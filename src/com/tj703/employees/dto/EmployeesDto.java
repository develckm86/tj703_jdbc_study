package com.tj703.employees.dto;

import java.util.Date;

public class EmployeesDto {
    /*emp_no
    birth_date
    first_name
    last_name
    gender
    hire_date*/
    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private char gender;
    private Date hireDate;

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "{" +
                "empNo:" + empNo +
                ", birthDate:" + birthDate +
                ", firstName:'" + firstName + '\'' +
                ", lastName:'" + lastName + '\'' +
                ", gender:" + gender +
                ", hireDate:" + hireDate +
                '}';
    }
}
