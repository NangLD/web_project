/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import comtext.DBContext;
import exception.MyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modle.Students;

/**
 *
 * @author Admin
 */
public class StudentDao extends HttpServlet {

    private Connection connection;
    HttpServletRequest request; HttpServletResponse response;
    public StudentDao(DBContext dbContext) throws MyException {
        try {
            connection = dbContext.getConnection(request, response);
        } catch (Exception ex) {
            throw new MyException(ex.hashCode(), ex);
        }
    }

    public List<Students> getStudents() throws MyException {
        List<Students> students = new ArrayList<Students>();
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from StudentInformation");
            while (rs.next()) {
                Students student = new Students();
                student.setId(rs.getInt("ID"));
                student.setFullName(rs.getString("Full Name"));
                student.setAddress(rs.getString("Address"));
                student.setPhoneNumber(rs.getInt("Phone Number"));
                student.setTeacher(rs.getString("Teacher"));
                student.setClassOfStudent(rs.getString("Class"));
                student.setAge(rs.getInt("Age"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new MyException(1001, e);
        }

        return students;
    }

    public void insertStudent(int id, String name, String address, int phoneNumber, String teacher, String classOfStudent, int age) throws MyException {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO StudentInformation(ID, [Full Name],Address, "
                            + "[Phone Number], Teacher, Class, Age)  \n"
                            + "VALUES (?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setInt(4, phoneNumber);
            preparedStatement.setString(5, teacher);
            preparedStatement.setString(6, classOfStudent);
            preparedStatement.setInt(7, age);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void updateStudent(int id, String name, String address, int phoneNumber, String teacher, String classOfStudent, int age) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE StudentInformation \n"
                            + "SET [Full Name] = ?, address=?, [Phone Number]=?, Teacher=?,Class=?,Age=?\n"
                            + "WHERE ID = ?;");

            preparedStatement.setInt(7, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, phoneNumber);
            preparedStatement.setString(4, teacher);
            preparedStatement.setString(5, classOfStudent);
            preparedStatement.setInt(6, age);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public void deleteStudent(int id) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("DELETE FROM StudentInformation\n"
                            + "WHERE Id = ?;");

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) throws MyException {
        DBContext db = new DBContext();
        StudentDao dao = new StudentDao(db);
        dao.deleteStudent(56);
    }
}
