/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import comtext.DBContext;
import exception.MyException;
import java.util.List;
import modle.Students;

/**
 *
 * @author Admin
 */
public class StudentManagement {
    StudentDao databaseDao;
    public StudentManagement(StudentDao databaseDao)
    {
        this.databaseDao = databaseDao;
    }
    public List<Students> getStudents() throws MyException
    {
        return databaseDao.getStudents();
    }
    public void insertStudent(int id, String name, String address, int phoneNumber, String teacher, String classOfStudent, int age ) throws MyException{
        databaseDao.insertStudent(id, name, address, phoneNumber, teacher, classOfStudent, age);
    }
    public void updateStudent(int id, String name, String address, int phoneNumber, String teacher, String classOfStudent, int age ) throws MyException{
        databaseDao.updateStudent(id, name, address, phoneNumber, teacher, classOfStudent, age);
    }
    public void deleteStudent(int id){
        databaseDao.deleteStudent(id);
    }
    public static void main(String[] args) throws MyException {
             DBContext dBContext = new DBContext();
            StudentDao databaseDao = new StudentDao(dBContext);
            StudentManagement studentManagement = new StudentManagement(databaseDao);
            studentManagement.updateStudent(7, "A", "A", 0, "B", "B", 0);
    }
}
