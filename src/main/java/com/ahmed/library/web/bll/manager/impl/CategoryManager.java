/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.manager.impl;

import com.ahmed.library.web.bll.beans.CategoryBean;
import com.ahmed.library.web.bll.manager.Manager;
import com.ahmed.library.web.bll.transformer.CategoryTransformer;
import com.ahmed.library.web.dal.entity.Category;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.beginTransaction;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.commitTransaction;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.openSession;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.rollbackTransaction;
import com.ahmed.library.web.dal.repository.impl.CategoryRepo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed Hafez
 */
public class CategoryManager implements Manager<CategoryBean> {

    private CategoryTransformer categoryTransformer = new CategoryTransformer();
    private CategoryRepo categoryRepo = new CategoryRepo();
    @Override
    public CategoryBean add(CategoryBean bean) throws Exception {
        try {
            validateCategory(bean);
            openSession();
            Category author = categoryTransformer.transformBeanToEntity(bean);
            beginTransaction();
            Category categoryEntity = categoryRepo.add(author);
            CategoryBean resultBean = categoryTransformer.transformEntityToBean(categoryEntity);
            commitTransaction();
            return resultBean;
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<CategoryBean> findList() {
        openSession();
        beginTransaction();
        List<Category> categoriesList = categoryRepo.findList();
        List<CategoryBean> resultCategoryBean= new ArrayList<>();
        for (Category category : categoriesList) {
            CategoryBean categoryBean = categoryTransformer.transformEntityToBean(category);
            resultCategoryBean.add(categoryBean);
        }
        commitTransaction();
        return resultCategoryBean;
    }

    @Override
    public CategoryBean edit(CategoryBean bean) throws Exception {
        try {
            validateCategory(bean);
            openSession();
            Category category = categoryTransformer.transformBeanToEntityWithId(bean);
            beginTransaction();
            Category categoryEntity = categoryRepo.update(category);
            CategoryBean resultBean = categoryTransformer.transformEntityToBean(categoryEntity);
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
            categoryRepo.remove(id);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }

    }

    @Override
    public CategoryBean get(Integer id) {
        openSession();
        beginTransaction();
        Category categoryEntity = categoryRepo.findById(id);
        CategoryBean resultBean = categoryTransformer.transformEntityToBean(categoryEntity);
        commitTransaction();
        return resultBean;
    }

    private void validateCategory(CategoryBean category) throws Exception {
        if (null == category) {
            throw new Exception("The category Bean is null");

        }
        if (null == category.getName() || category.getName().isEmpty()) {
            throw new Exception("Category Name should not to be Empty or Null");
        }
        if (0 != getByName(category.getName().trim())) {
            throw new Exception("Category Name Is exists you should change it");
        }
    }

    public int getByName(String name) {
        openSession();
        beginTransaction();
        int size = categoryRepo.findByName(name);
        commitTransaction();
        return size;
    }
}