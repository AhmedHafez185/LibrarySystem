/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.manager.impl;

import com.ahmed.library.web.auth.CryptWithMD5;
import com.ahmed.library.web.bll.beans.UserBean;
import com.ahmed.library.web.bll.manager.Manager;
import com.ahmed.library.web.bll.transformer.UserTransformer;
import com.ahmed.library.web.dal.entity.User;
import com.ahmed.library.web.dal.repository.impl.UserRepo;
import java.util.List;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @user Ahmed Hafez
 */
public class UserManager implements Manager<UserBean> {

    private UserTransformer userTransformer = new UserTransformer();
    private UserRepo userRepo = new UserRepo();

    @Override
    public UserBean add(UserBean bean) throws Exception {
        try {
            validateUser(bean, "insert");
            System.out.println("Password is ok Ahmed MB in add after check()");
            openSession();
            User user = userTransformer.transformBeanToEntity(bean);
            user.setPassword(encryptPassword(bean.getPassword()));
            beginTransaction();
            User userEntity = userRepo.add(user);
            UserBean resultBean = userTransformer.transformEntityToBean(userEntity);
            commitTransaction();
            return resultBean;
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<UserBean> findList() {
        openSession();
        beginTransaction();
        List<User> usersList = userRepo.findList();
        List<UserBean> resultUserBean = new ArrayList<>();
        for (User user : usersList) {
            UserBean userBean = userTransformer.transformEntityToBean(user);
            resultUserBean.add(userBean);
        }
        commitTransaction();
        return resultUserBean;
    }

    @Override
    public UserBean edit(UserBean bean) throws Exception {
        try {
            validateUser(bean, "update");
            openSession();
            User user = userTransformer.transformBeanToEntityWithId(bean);
            beginTransaction();
            User userEntity = userRepo.update(user);
            UserBean resultBean = userTransformer.transformEntityToBean(userEntity);
            commitTransaction();
            return resultBean;
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }

    }

    @Override
    public void remove(int id) throws Exception {
        try {
            openSession();
            beginTransaction();
            userRepo.remove(id);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }

    }

    public UserBean loginAdmin(String username, String password) throws Exception {
        try {
            String encrptedPassword = encryptPassword(password);
            validateLogin(username, password);
            openSession();
            beginTransaction();
            User userEntity = userRepo.loginAdmin(username, encrptedPassword);
            
            UserBean resultBean = userTransformer.transformEntityToBean(userEntity);
            commitTransaction();
            return resultBean;
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }

    }

    @Override
    public UserBean get(Integer id) {
        openSession();
        beginTransaction();
        User userEntity = userRepo.findById(id);
        UserBean resultBean = userTransformer.transformEntityToBean(userEntity);
        commitTransaction();
        return resultBean;
    }

    private void validateLogin(String username, String password) throws Exception {

        if (null == username || username.isEmpty()) {
            throw new Exception("Username should not to be Empty or Null");
        }
        if (null == password || password.isEmpty()) {
            throw new Exception("User Password should not to be Empty or Null");
        }
    }

    private void validateUser(UserBean user, String method) throws Exception {
        if (null == user) {
            throw new Exception("The User Bean is null");

        }
        if (null == user.getFullName() || user.getFullName().isEmpty()) {
            throw new Exception("User Full Name should not to be Empty or Null");
        }
        if (null == user.getUsername() || user.getUsername().isEmpty()) {
            throw new Exception("Username should not to be Empty or Null");
        }
        if (null == user.getPassword() || user.getPassword().isEmpty()) {
            throw new Exception("User Password should not to be Empty or Null");
        }
        if (0 != getByName(user.getUsername().trim())) {
            if("update".endsWith(method)){
                if(!(Objects.equals(user.getId(), getByName(user.getUsername().trim())))){
                    throw new Exception("This  username Is exists you have to change it");
                }
            }else{
            throw new Exception("This  username Is exists you have to change it");
            }
        }

    }

    public Integer getByName(String name) {
        System.out.println("first in getbyname");
        openSession();
        beginTransaction();
        Integer userId = userRepo.findByName(name);
        System.out.println("second in getbyname "+userId);
        commitTransaction();
        return userId;
    }

    public String encryptPassword(String password) {
        String encryptedPass = CryptWithMD5.encryptWithMD5(password);
        return encryptedPass;
    }

    public boolean comparePasswords(String password1, String password2) {
        System.out.println("Password is ok Ahmed in compare password");
        return password1.equals(password2);
    }

    public boolean verfiyPassword(UserBean user) {
        String encryptPassword = encryptPassword(user.getPassword());
        return true;
    }
}
