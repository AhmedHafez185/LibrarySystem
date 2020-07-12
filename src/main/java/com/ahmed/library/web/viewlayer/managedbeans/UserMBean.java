/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.viewlayer.managedbeans;
import com.ahmed.library.web.auth.SessionUtils;
import com.ahmed.library.web.bll.beans.UserBean;
import com.ahmed.library.web.bll.manager.impl.UserManager;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @user Ahmed Hafez
 */
@ManagedBean(name = "userMBean")
@SessionScoped
public class UserMBean implements Serializable {

    UserManager userManager;
    UserBean userBean;
    List<UserBean> users;
    String errorMessage = "";
    private String infoMessage = "";
    private boolean checkMessage = false;

    /**
     * Creates a new instance of UserMBean
     */
    public UserMBean() {
        try {
            userBean = new UserBean();
            userManager = new UserManager();
            reloadUsers();
        } catch (Exception ex) {

        }
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean UserBean) {
        this.userBean = UserBean;
    }

    public List<UserBean> getUsers() {
        return users;
    }

    public void setUsers(List<UserBean> users) {
        this.users = users;
    }

    private void reloadUsers() {
        setErrorMessage("");
        users = userManager.findList();

    }

    public String create() {
        setErrorMessage("");
        userBean = new UserBean();
        return "user_form";
    }

    public String save() {
        setInfoMessage("");
        try {
            if (userBean.getId() == null) {
                System.out.println("P1:"+userBean.getPassword()+", P2:"+userBean.getConfirm_password());
                if(!(userManager.comparePasswords(userBean.getPassword(),userBean.getConfirm_password())))
                {
                //    System.out.println("Password is ok Ahmed MB in save()");
                    setInfoMessage("Error !! Two Password Not matches");
                    setErrorMessage("Error !! Two Password Not matches");
                    return "user_form";
                }
                userManager.add(userBean);
                setInfoMessage("User Added Successfully");
            } else {
                userManager.edit(userBean);
                setInfoMessage("User Updated Successfully");
            }
            reloadUsers();
            setErrorMessage("");
            setCheckMessage(true);
            saveMessage();
            return "users";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            setCheckMessage(true);
            if (userBean.getId() == null)
                return "user_form";
            else
                return "user_edit";
        }

    }

    public String login() {
        setInfoMessage("");
        try {
            UserBean VerifiedUser = userManager.loginAdmin(userBean.getUsername(),userBean.getPassword());
            if(VerifiedUser == null){
                 setErrorMessage("Username or Password is incorrect !");
                 setInfoMessage("Username or Password is incorrect !");
                 return "login";
            }else{
                 setErrorMessage("Welcome "+VerifiedUser.getFullName());
                 setInfoMessage("Welcome "+VerifiedUser.getFullName());
                 HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", VerifiedUser.getUsername());
                        session.setAttribute("fullName", VerifiedUser.getFullName());
                 return "index";
            }
        } catch (Exception ex) {
            setErrorMessage("Username or Password is incorrect !");
            setCheckMessage(true);
            return "login";
        }

    }

    public String edit(Integer id) {
        try {
            userBean = userManager.get(id);
            return "user_edit";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            return "user_edit";
        }
    }

    public void remove(Integer id) {
        setInfoMessage("");
        try {
            userManager.remove(id);
            reloadUsers();
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
    public String getUserType(boolean type){
        if(type)
            return "Admin";
        return "Normal User";
    }
    // <editor-fold desc="SETTER & GETTER>
    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
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

	//logout event, invalidate session
	public String logout() {
		          HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
  
}
