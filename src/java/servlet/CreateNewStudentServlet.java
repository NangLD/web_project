/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import comtext.DBContext;
import exception.MyException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modle.Students;
import student.StudentDao;
import student.StudentManagement;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateNewStudentServlet", urlPatterns = {"/CreateNew"})
public class CreateNewStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("isCreatedSuccessful", false);
            DBContext dBContext = new DBContext();
            StudentDao databaseDao = new StudentDao(dBContext);
            StudentManagement studentManagement = new StudentManagement(databaseDao);
            List<Students> students = studentManagement.getStudents();

            request.setAttribute("students", students);
            request.setAttribute("id", "");
            request.setAttribute("isEmptyField", false);

            RequestDispatcher view = request.getRequestDispatcher("/create.jsp");
            view.forward(request, response);
        } catch (MyException ex) {
            request.setAttribute("error", ex);
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/exceptions/error.jsp");
            view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isEmptyField = false;
        request.setAttribute("isEmptyField", false);
        request.setAttribute("isCreatedSuccessful", false);
        try {
            DBContext dBContext = new DBContext();
            StudentDao databaseDao = new StudentDao(dBContext);
            StudentManagement studentManagement = new StudentManagement(databaseDao);
            List<Students> students = studentManagement.getStudents();

            request.setAttribute("students", students);
            request.setAttribute("id", request.getParameter("id"));
            request.setAttribute("fullName", request.getParameter("full_name"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("phoneNumber", request.getParameter("phone_number"));
            request.setAttribute("teacher", request.getParameter("teacher"));
            request.setAttribute("class", request.getParameter("class"));
            request.setAttribute("age", request.getParameter("age"));

            int id = Integer.parseInt(request.getParameter("id"));///note
            for (Students std : students) {
                if (id == std.getId()) {
                    RequestDispatcher view = request.getRequestDispatcher("/create.jsp");
                    view.forward(request, response);
                    return;
                }
            }

            String fullName = request.getParameter("full_name");
            String address = request.getParameter("address");
            int phoneNumber = Integer.parseInt(request.getParameter("phone_number"));
            String teacher = request.getParameter("teacher");
            String classOfStudent = request.getParameter("class");
            int age = Integer.parseInt(request.getParameter("age"));
            request.setAttribute("age", request.getParameter("age"));

            if (fullName.trim().equals("") || address.trim().equals("") || teacher.trim().equals("") || classOfStudent.trim().equals("")) {
                isEmptyField = true;
                request.setAttribute("isEmptyField", isEmptyField);
                RequestDispatcher view = request.getRequestDispatcher("/create.jsp");
                view.forward(request, response);
                return;
            }
            request.setAttribute("isCreatedSuccessful", true);
            studentManagement.insertStudent(id, fullName, address, phoneNumber, teacher, classOfStudent, age);
            RequestDispatcher view = request.getRequestDispatcher("/create.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            isEmptyField = true;
            request.setAttribute("isEmptyField", isEmptyField);
            RequestDispatcher view = request.getRequestDispatcher("/create.jsp");
            view.forward(request, response);
            return;

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
