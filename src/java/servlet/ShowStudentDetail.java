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
@WebServlet(name = "ShowStudentDetail", urlPatterns = {"/ShowStudentDetail"})
public class ShowStudentDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowStudentDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowStudentDetail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ID = request.getParameter("ID");
            
            List<Students> students = null;

            DBContext dBContext = new DBContext();
            StudentDao databaseDao = new StudentDao(dBContext);
            StudentManagement studentManagement = new StudentManagement(databaseDao);

            students = studentManagement.getStudents();
            
            for (Students student : students) {
                if (ID.equals(student.getId() + "")) {
                    request.setAttribute("student", student);
                    RequestDispatcher view = request.getRequestDispatcher("/detail.jsp");
                    view.forward(request, response);
                    return;
                }
            }
            
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/exceptions/no_student.jsp");
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
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

}
