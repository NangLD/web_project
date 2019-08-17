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
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modle.Students;
import student.StudentDao;
import student.StudentManagement;

@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Object o = request.getAttribute("isDeletedDone");
            if (o == null) {
                request.setAttribute("isDeletedDone", false);
                request.setAttribute("ten", "");
            } else {
                request.setAttribute("isDeletedDone", true);
                request.setAttribute("ten", request.getAttribute("ten"));
            }

            DBContext dBContext = new DBContext();
            StudentDao databaseDao = new StudentDao(dBContext);
            System.out.println("aaaa");
            StudentManagement studentManagement = new StudentManagement(databaseDao);

            List<Students> students = studentManagement.getStudents();

            request.setAttribute("students", students);

            RequestDispatcher view = request.getRequestDispatcher("/students.jsp");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
