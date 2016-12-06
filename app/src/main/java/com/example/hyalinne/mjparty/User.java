package com.example.hyalinne.mjparty;

/**
 * Created by hyalinne on 2016-12-07.
 */
public class User {
    private String email;
    private String passwd;
    private String name;
    private int age;
    private String gender;
    private String major;

    public User(String email, String passwd, String name, int age, String gender, String major) {
        this.email = email;
        this.passwd = passwd;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
