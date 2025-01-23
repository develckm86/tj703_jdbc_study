package com.tj703.employees.dto;

import java.util.Date;

public class SalariesDto {
    private int empNo;
    private int salary;
    private Date fromDate;
    private Date toDate;

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "{" +
                "empNo:" + empNo +
                ", salary:" + salary +
                ", fromDate:" + fromDate +
                ", toDate:" + toDate +
                "}";
    }
}
