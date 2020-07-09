/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.viewlayer.managedbeans;

import com.ahmed.library.web.bll.beans.CategoryBean;
import com.ahmed.library.web.bll.manager.impl.CategoryManager;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ahmed Hafez
 */
@ManagedBean(name = "categoryMBean")
@SessionScoped
public class CategoryMBean implements Serializable {

    CategoryManager categoryManager;
    CategoryBean categoryBean;
    List<CategoryBean> categories;
    String errorMessage = "";

    /**
     * Creates a new instance of CategoryMBean
     */
    public CategoryMBean() {
        try {
            categoryBean = new CategoryBean();
            categoryManager = new CategoryManager();
            reloadCategories();
        } catch (Exception ex) {

        }
    }

    private void reloadCategories() {
        categories = categoryManager.findList();

    }

    public String create() {
        categoryBean = new CategoryBean();
        return "category_form";
    }

    public String save() {
        String page = "";
        String Message = "";
        try {
            if (categoryBean.getId() == null) {
                categoryManager.add(categoryBean);
                reloadCategories();
                Message = "Category Added Successfully";
            } else {
                categoryManager.edit(categoryBean);
                reloadCategories();
                Message = "Category Updated Successfully";
            }
           
            FacesMessage msg = new FacesMessage("Saved",Message);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            page = "category";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            page = "category_form";
        } finally {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            return page;
        }

    }

    public String edit(Integer id) {
        try {
            categoryBean = categoryManager.get(id);

            return "category_form";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            return "category_form";
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void remove(Integer id) {
        String Message = "";
        try {
            categoryManager.remove(id);
            reloadCategories();
            Message = "Record Deleted Successfully";
        } catch (Exception ex) {
            Message = "Error : " + ex.getMessage();
        }
        FacesMessage msg = new FacesMessage(Message);
        FacesContext.getCurrentInstance().addMessage("growlNotifaction", msg);
    }

    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    public CategoryBean getCategoryBean() {
        return categoryBean;
    }

    public void setCategoryBean(CategoryBean categoryBean) {
        this.categoryBean = categoryBean;
    }

    public List<CategoryBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryBean> categories) {
        this.categories = categories;
    }

}
