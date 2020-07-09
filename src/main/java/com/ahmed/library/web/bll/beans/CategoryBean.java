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
public class CategoryBean {
     private Integer id;
     private String name;

    public CategoryBean() {
    }

    public CategoryBean(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryBean(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
     
}
