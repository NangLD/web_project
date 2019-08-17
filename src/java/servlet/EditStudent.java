/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import comtext.DBContext;
import exception.MyException;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditStudent", urlPatterns = {"/EditStudent"})
public class EditStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("isEditedDone", false);
            String ID = request.getParameter("ID");
            // request.setAttribute("ID", ID);

            List<Students> students = null;

            DBContext dBContext = new DBContext();
            StudentDao databaseDao = new StudentDao(dBContext);
            StudentManagement studentManagement = new StudentManagement(databaseDao);
            students = studentManagement.getStudents();
            request.setAttribute("students", students);
            boolean isExisted = false;
            for (Students student : students) {
                if (ID.equals(student.getId() + "")) {
                    request.setAttribute("fullName", student.getFullName());
                    request.setAttribute("address", student.getAddress());
                    request.setAttribute("phoneNumber", student.getPhoneNumber() + "");
                    request.setAttribute("teacher", student.getTeacher());
                    request.setAttribute("class", student.getClassOfStudent());
                    request.setAttribute("age", student.getAge() + "");
                    isExisted = true;
                }
            }
            if (!isExisted) {
                RequestDispatcher view = request.getRequestDispatcher("WEB-INF/exceptions/no_student.jsp");
                view.forward(request, response);
                return;
            }
            request.setAttribute("isEmptyField", false);
            RequestDispatcher view = request.getRequestDispatcher("/edit.jsp");
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
        request.setAttribute("isEditedDone", false);
        boolean isEmptyField = false;
        request.setAttribute("isEmptyField", false);
        try {
            DBContext dBContext = new DBContext();
            StudentDao databaseDao = new StudentDao(dBContext);
            StudentManagement studentManagement = new StudentManagement(databaseDao);
            List<Students> students = studentManagement.getStudents();

            request.setAttribute("students", students);
            request.setAttribute("fullName", request.getParameter("full_name"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("phoneNumber", request.getParameter("phone_number"));
            request.setAttribute("teacher", request.getParameter("teacher"));
            request.setAttribute("class", request.getParameter("class"));
            request.setAttribute("age", request.getParameter("age"));

            int id = Integer.parseInt(request.getParameter("ID"));///note
            String fullName = request.getParameter("full_name");
            String address = request.getParameter("address");
            int phoneNumber = Integer.parseInt(request.getParameter("phone_number"));
            String teacher = request.getParameter("teacher");
            String classOfStudent = request.getParameter("class");
            int age = Integer.parseInt(request.getParameter("age"));

            if (fullName.trim().equals("") || address.trim().equals("") || teacher.trim().equals("") || classOfStudent.trim().equals("")) {
                isEmptyField = true;
                request.setAttribute("isEmptyField", isEmptyField);
                RequestDispatcher view = request.getRequestDispatcher("/edit.jsp");
                view.forward(request, response);
                return;
            }
            studentManagement.updateStudent(id, fullName, address, phoneNumber, teacher, classOfStudent, age);
            request.setAttribute("isEditedDone", true);
            RequestDispatcher view = request.getRequestDispatcher("/edit.jsp");
                view.forward(request, response);
        } catch (Exception ex) {
            isEmptyField = true;
            request.setAttribute("isEmptyField", isEmptyField);
            RequestDispatcher view = request.getRequestDispatcher("/edit.jsp");
            view.forward(request, response);
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
