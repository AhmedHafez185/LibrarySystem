/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.dal.repository.impl;

import com.ahmed.library.web.dal.entity.Category;
import com.ahmed.library.web.dal.repository.AbstractRepo;

/**
 *
 * @author Ahmed Hafez
 */
public class CategoryRepo extends AbstractRepo<Category>{
    
    public CategoryRepo() {
        super(Category.class);
    }
    
}
