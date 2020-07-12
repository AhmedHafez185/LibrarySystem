/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.beans;
/**
 *
 * @author Ahmed Hafez
 */
public class UserBean {
     private Integer id;
     private String username;
     private String fullName;
     private String picute;
     private String password;
     private String confirm_password;
     private boolean userType;

    public UserBean(Integer id, String username, String password, boolean userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public UserBean(String username, String password, boolean userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public UserBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPicute() {
        return picute;
    }

    public void setPicute(String picute) {
        this.picute = picute;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
     
}
