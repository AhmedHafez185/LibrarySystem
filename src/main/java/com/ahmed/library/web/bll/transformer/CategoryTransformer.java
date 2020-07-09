/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.transformer;

import com.ahmed.library.web.bll.beans.CategoryBean;
import com.ahmed.library.web.dal.entity.Category;

/**
 *
 * @author Ahmed Hafez
 */
public class CategoryTransformer implements GeneralTransformer<Category,CategoryBean>{

    @Override
    public CategoryBean transformEntityToBean(Category entity) {
         CategoryBean categoryBean = new CategoryBean();
          categoryBean.setName(entity.getName());
          categoryBean.setId(entity.getId());          
          return categoryBean;
    }

    @Override
    public Category transformBeanToEntity(CategoryBean bean) {
        Category category = new Category();
          category.setName(bean.getName());
          return category;
    }

    @Override
    public Category transformBeanToEntityWithId(CategoryBean bean) {
        Category category = new Category();
         category.setId(bean.getId());
         category.setName(bean.getName());
          return category;
    }
}
