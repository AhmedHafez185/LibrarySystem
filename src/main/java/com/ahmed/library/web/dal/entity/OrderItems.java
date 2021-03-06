package com.ahmed.library.web.dal.entity;
// Generated Jun 25, 2020 11:00:16 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderItems generated by hbm2java
 */
@Entity
@Table(name="order_items"
    ,catalog="library"
)
public class OrderItems  implements java.io.Serializable {


     private int id;
     private Book book;
     private Order order;
     private int quantity;
     private float price;

    public OrderItems() {
    }

    public OrderItems(int id, Book book, Order order, int quantity, float price) {
       this.id = id;
       this.book = book;
       this.order = order;
       this.quantity = quantity;
       this.price = price;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id", nullable=false)
    public Book getBook() {
        return this.book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    
    @Column(name="quantity", nullable=false)
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    @Column(name="price", nullable=false, precision=12, scale=0)
    public float getPrice() {
        return this.price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }




}


