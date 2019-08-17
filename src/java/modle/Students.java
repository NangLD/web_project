/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

/**
 *
 * @author Admin
 */
public class Students {
    private int id;
    private String fullName;
    private String address;
    private int phoneNumber;
    private String teacher;
    private String classOfStudent;
    private int age;

    public Students() {
    }

    public Students(int id, String fullName, String address, int phoneNumber, String teacher, String classOfStudent, int age) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.teacher = teacher;
        this.classOfStudent = classOfStudent;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getClassOfStudent() {
        return classOfStudent;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setClassOfStudent(String classOfStudent) {
        this.classOfStudent = classOfStudent;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
