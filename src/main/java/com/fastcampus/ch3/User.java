package com.fastcampus.ch3;

import java.util.Date;
import java.util.Objects;

public class User {

    private String id;
    private String pwd;
    private String name;
    private String email;
    private String sns;
    private Date birth;
    private Date regDate;

    public User() {
    }

    public User(String id, String pwd, String name, String email, String sns, Date birth, Date regDate) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.sns = sns;
        this.birth = birth;
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && Objects.equals(pwd, user.pwd) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(sns, user.sns) && Objects.equals(birth, user.birth) && Objects.equals(regDate, user.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pwd, name, email, sns, birth, regDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sns='" + sns + '\'' +
                ", birth=" + birth +
                ", regDate=" + regDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSns() {
        return sns;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }


}
