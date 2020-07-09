/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.viewlayer.managedbeans;

import com.ahmed.library.web.bll.beans.AuthorBean;
import com.ahmed.library.web.bll.manager.impl.AuthorManager;
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
@ManagedBean(name = "authorMBean")
@SessionScoped
public class AuthorMBean implements Serializable {

    AuthorManager authorManager;
    AuthorBean authBean;
    List<AuthorBean> authors;
    String errorMessage = "";
    private String infoMessage = "";
    private boolean checkMessage = false;
    /**
     * Creates a new instance of AuthorMBean
     */
    public AuthorMBean() {
        try {
            authBean = new AuthorBean();
            authorManager = new AuthorManager();
            reloadAuthors();
        } catch (Exception ex) {

        }
    }

    public AuthorBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthorBean authBean) {
        this.authBean = authBean;
    }

    public List<AuthorBean> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorBean> authors) {
        this.authors = authors;
    }

    private void reloadAuthors() {
        setErrorMessage("");
        authors = authorManager.findList();

    }

    public String create() {
        setErrorMessage("");
        authBean = new AuthorBean();
        return "author_form";
    }

    public String save() {
        setInfoMessage("");
        try {
            if (authBean.getId() == null) {
                authorManager.add(authBean);
                setInfoMessage("Author Added Successfully");
            } else {
                authorManager.edit(authBean);
                setInfoMessage("Author Updated Successfully");
            }
            reloadAuthors();
            setErrorMessage("");
             setCheckMessage(true);
            saveMessage();
            return "authors";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
             setCheckMessage(true);
            return "author_form";
        }

    }

    public String edit(Integer id) {
        try {
            authBean = authorManager.get(id);

            return "author_form";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            return "author_form";
        }
    }

    public void remove(Integer id) {
        setInfoMessage("");
        try {
            authorManager.remove(id);
            reloadAuthors();
            setInfoMessage("Record Deleted Successfully");
        } catch (Exception ex) {
            setInfoMessage("Error : " + ex.getMessage());
        }
        setCheckMessage(true);
        saveMessage();
        setCheckMessage(false);
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(getInfoMessage()));
    }
    // <editor-fold desc="SETTER & GETTER>
    public AuthorManager getAuthorManager() {
        return authorManager;
    }

    public void setAuthorManager(AuthorManager authorManager) {
        this.authorManager = authorManager;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isCheckMessage() {
        return checkMessage;
    }

    public void setCheckMessage(boolean checkMessage) {
        this.checkMessage = checkMessage;
    }
    //</editor-fold>
}
