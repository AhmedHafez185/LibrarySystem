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
public class BookBean {
     private Integer id;
     private AuthorBean author;
     private CategoryBean category;
     private String name;
     private float price;
    public BookBean(){
        
    }

    public BookBean(AuthorBean author, CategoryBean category, String name, float price) {
        this.author = author;
        this.category = category;
        this.name = name;
        this.price = price;
    }

   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
}
