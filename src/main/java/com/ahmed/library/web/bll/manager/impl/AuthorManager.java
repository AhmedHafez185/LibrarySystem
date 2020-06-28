/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.manager.impl;

import com.ahmed.library.web.bll.beans.AuthorBean;
import com.ahmed.library.web.bll.manager.Manager;
import com.ahmed.library.web.bll.transformer.AuthorTransformer;
import com.ahmed.library.web.dal.entity.Author;
import com.ahmed.library.web.dal.repository.impl.AuthorRepo;
import java.util.List;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.*;
import java.util.ArrayList;

/**
 *
 * @author Ahmed Hafez
 */
public class AuthorManager implements Manager<AuthorBean> {

    private AuthorTransformer authorTransformer = new AuthorTransformer();
    private AuthorRepo authorRepo = new AuthorRepo();
    @Override
    public AuthorBean add(AuthorBean bean) throws Exception {
        try {
            validateAuthor(bean);
            openSession();
            Author author = authorTransformer.transformBeanToEntity(bean);
            beginTransaction();
            Author authorEntity = authorRepo.add(author);
            AuthorBean resultBean = authorTransformer.transformEntityToBean(authorEntity);
            commitTransaction();
            return resultBean;
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<AuthorBean> findList() {
        openSession();
        beginTransaction();
        List<Author> authorsList = authorRepo.findList();
        List<AuthorBean> resultAuthorBean = new ArrayList<>();
        for (Author author : authorsList) {
            AuthorBean authorBean = authorTransformer.transformEntityToBean(author);
            resultAuthorBean.add(authorBean);
        }
        commitTransaction();
        return resultAuthorBean;
    }

    @Override
    public AuthorBean edit(AuthorBean bean) throws Exception {
        try {
            validateAuthor(bean);
            openSession();
            Author author = authorTransformer.transformBeanToEntityWithId(bean);
            beginTransaction();
            Author authorEntity = authorRepo.update(author);
            AuthorBean resultBean = authorTransformer.transformEntityToBean(authorEntity);
            commitTransaction();
            return resultBean;
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }

    }

    @Override
    public void remove(int id) throws Exception{
        try {
            openSession();
            beginTransaction();
            authorRepo.remove(id);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }

    }

    @Override
    public AuthorBean get(Integer id) {
        openSession();
        beginTransaction();
        Author authorEntity = authorRepo.findById(id);
        AuthorBean resultBean = authorTransformer.transformEntityToBean(authorEntity);
        commitTransaction();
        return resultBean;
    }

    private void validateAuthor(AuthorBean author) throws Exception {
        if (null == author) {
            throw new Exception("The Author Bean is null");

        }
        if (null == author.getName() || author.getName().isEmpty()) {
            throw new Exception("Author Name should not to be Empty or Null");
        }
        if (0 != getByName(author.getName().trim())) {
            throw new Exception("Author Name Is exists you should change it");
        }
    }

    public int getByName(String name) {
        openSession();
        beginTransaction();
        int size = authorRepo.findByName(name);
        System.out.println("Found Element = " + size);
        commitTransaction();
        return size;
    }
}
