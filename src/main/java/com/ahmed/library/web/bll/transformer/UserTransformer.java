/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.transformer;


import com.ahmed.library.web.bll.beans.UserBean;
import com.ahmed.library.web.dal.entity.User;

/**
 *
 * @user Ahmed Hafez
 */
public class UserTransformer implements GeneralTransformer<User, UserBean>{

    @Override
    public UserBean transformEntityToBean(User entity) {
          UserBean userBean = new UserBean();
          userBean.setFullName(entity.getFullName());
          userBean.setUsername(entity.getUsername());
          userBean.setId(entity.getId());
          userBean.setPicute(entity.getPicture());
          userBean.setUserType(entity.isUserType());
          userBean.setPassword(entity.getPassword());
          return userBean;
    }

    @Override
    public User transformBeanToEntity(UserBean bean) {
         User user = new User();
         user.setFullName(bean.getFullName());
          user.setUsername(bean.getUsername());
          user.setId(bean.getId());
          user.setPicture(bean.getPicute());
          user.setUserType(bean.isUserType());
          user.setPassword(bean.getPassword());   
          return user;
    }
    @Override
    public User transformBeanToEntityWithId(UserBean bean) {
        User user = new User();
         user.setFullName(bean.getFullName());
          user.setUsername(bean.getUsername());
          user.setPicture(bean.getPicute());
          user.setUserType(bean.isUserType());
          user.setPassword(bean.getPassword());   
          return user;
    }
     
}
