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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    AuthorBean authBean ;
    List<AuthorBean> authors;
    String errorMessage = "";
    /**
     * Creates a new instance of AuthorMBean
     */
    public AuthorMBean() {
        try{
            authBean = new AuthorBean();
        authorManager = new AuthorManager();
        reloadAuthors();
        }catch(Exception ex){
            
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
    private void reloadAuthors(){
        authors = authorManager.findList();
        
    }
    public String create(){
        authBean = new AuthorBean();
        return "addAuthor";
    }
    public String save(){
        String Message = "";
        try {
            if(authBean.getId() == null){
            authorManager.add(authBean);
            Message = "Author Added Successfully";
            }else{
                System.out.println("Name : "+authBean.getName());
                 authorManager.edit(authBean);
                 Message = "Author Updated Successfully";
            }
            reloadAuthors();
            setErrorMessage("");
            FacesMessage msg = new FacesMessage(Message);
        FacesContext.getCurrentInstance().addMessage("growlNotifaction", msg);
            return "authors";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            return "addAuthor";
        }
        
        
    }
    public String edit(Integer id){
        try {
            authBean = authorManager.get(id);
            
            return "addAuthor";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            return "addAuthor";
        } 
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public void remove(Integer id){
        String Message="";
        try {
            authorManager.remove(id);
            reloadAuthors();
            Message = "Record Deleted Successfully";
        } catch (Exception ex) {
            Message = "Error : "+ex.getMessage();
        }
        FacesMessage msg = new FacesMessage(Message);
        FacesContext.getCurrentInstance().addMessage("growlNotifaction", msg);
    }
    
}
