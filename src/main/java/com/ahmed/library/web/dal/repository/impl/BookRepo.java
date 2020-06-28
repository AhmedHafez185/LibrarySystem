/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.dal.repository.impl;

import com.ahmed.library.web.dal.entity.Book;
import com.ahmed.library.web.dal.repository.AbstractRepo;

/**
 *
 * @author Ahmed Hafez
 */
public class BookRepo extends AbstractRepo<Book>{
    
    public BookRepo() {
        super(Book.class);
    }
    
}
