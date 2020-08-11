package com.meet.collegeplacement;

public class Hero {

    private String company;
    private String salary;
    private String time;
    private String _id;



    public Hero() {

    }

    public Hero(String company, String salary, String time, String _id) {
        this.company = company;
        this.salary = salary;
        this.time = time;
        this._id = _id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
