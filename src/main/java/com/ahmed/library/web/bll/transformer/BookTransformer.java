/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.transformer;

import com.ahmed.library.web.bll.beans.BookBean;
import com.ahmed.library.web.bll.beans.CategoryBean;
import com.ahmed.library.web.dal.entity.Author;
import com.ahmed.library.web.dal.entity.Book;
import com.ahmed.library.web.dal.entity.Category;

/**
 *
 * @author Ahmed Hafez
 */
public class BookTransformer implements GeneralTransformer<Book,BookBean>{
    CategoryTransformer categoryTransformer = new CategoryTransformer();
   AuthorTransformer authorTransformer = new AuthorTransformer();
    @Override
    public BookBean transformEntityToBean(Book entity) {
         BookBean bookBean = new BookBean();
          bookBean.setName(entity.getName());
          bookBean.setAuthor(authorTransformer.transformEntityToBean(entity.getAuthor()));
          bookBean.setCategory(categoryTransformer.transformEntityToBean(entity.getCategory()));
          bookBean.setPrice(entity.getPrice());
          bookBean.setId(entity.getId());          
          return bookBean;
    }

    @Override
    public Book transformBeanToEntity(BookBean bean) {
        Author author = authorTransformer.transformBeanToEntityWithId(bean.getAuthor());
        Category category = categoryTransformer.transformBeanToEntityWithId(bean.getCategory());
          Book bookEntity = new Book();
          bookEntity.setName(bean.getName());
          bookEntity.setAuthor(author);
          bookEntity.setCategory(category);
          bookEntity.setPrice(bean.getPrice());
          return bookEntity;
    }

    @Override
    public Book transformBeanToEntityWithId(BookBean bean) {
       Book bookEntity = new Book();
          bookEntity.setName(bean.getName());
          bookEntity.setAuthor(authorTransformer.transformBeanToEntityWithId(bean.getAuthor()));
          bookEntity.setCategory(categoryTransformer.transformBeanToEntityWithId(bean.getCategory()));
          bookEntity.setPrice(bean.getPrice());
          bookEntity.setId(bean.getId());          
          return bookEntity;
    }
}
