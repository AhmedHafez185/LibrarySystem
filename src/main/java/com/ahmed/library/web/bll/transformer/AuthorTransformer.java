/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.transformer;


import com.ahmed.library.web.bll.beans.AuthorBean;
import com.ahmed.library.web.dal.entity.Author;

/**
 *
 * @author Ahmed Hafez
 */
public class AuthorTransformer implements GeneralTransformer<Author, AuthorBean>{

    @Override
    public AuthorBean transformEntityToBean(Author entity) {
         AuthorBean authorBean = new AuthorBean();
          authorBean.setName(entity.getName());
          authorBean.setId(entity.getId());
          authorBean.setCountry(entity.getCountry());
          
          return authorBean;
    }

    @Override
    public Author transformBeanToEntity(AuthorBean bean) {
         Author author = new Author();
          author.setName(bean.getName());
          author.setCountry(bean.getCountry());    
          return author;
    }
    @Override
    public Author transformBeanToEntityWithId(AuthorBean bean) {
         Author author = new Author();
         author.setId(bean.getId());
          author.setName(bean.getName());
          author.setCountry(bean.getCountry());    
          return author;
    }
     
}
