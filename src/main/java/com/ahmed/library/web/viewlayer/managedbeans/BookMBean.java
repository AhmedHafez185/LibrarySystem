/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.viewlayer.managedbeans;

import com.ahmed.library.web.bll.beans.AuthorBean;
import com.ahmed.library.web.bll.beans.BookBean;
import com.ahmed.library.web.bll.beans.CategoryBean;
import com.ahmed.library.web.bll.manager.impl.AuthorManager;
import com.ahmed.library.web.bll.manager.impl.BookManager;
import com.ahmed.library.web.bll.manager.impl.CategoryManager;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @book Ahmed Hafez
 */
@ManagedBean(name = "bookMBean")
@SessionScoped
public class BookMBean implements Serializable {

    BookManager bookManager;
    CategoryManager categoryManager;
    AuthorManager authorManager;
    private List<CategoryBean> categories;
    private List<AuthorBean> author;
    BookBean bookBean;
    List<BookBean> books;
    String errorMessage = "";
    private String infoMessage = "";
    private Integer selectedAuthor;
    private Integer selectedCategory;
    private boolean checkMessage = false;
    private AuthorBean authorBean;
    private CategoryBean categoryBean;
    private BookBean selectedBook;

    /**
     * Creates a new instance of BookMBean
     */
    public BookMBean() {
        try {
            bookBean = new BookBean();
            categoryBean = new CategoryBean();
            authorBean = new AuthorBean();
            bookManager = new BookManager();
            authorManager = new AuthorManager();
            categoryManager = new CategoryManager();
            reloadBooks();
        } catch (Exception ex) {

        }
    }

    public BookBean getBookBean() {
        return bookBean;
    }

    public void setBookBean(BookBean bookBean) {
        this.bookBean = bookBean;
    }

    public List<BookBean> getBooks() {
        return books;
    }

    public void setBooks(List<BookBean> books) {
        this.books = books;
    }

    private void reloadBooks() {
        setErrorMessage("");
        books = bookManager.findList();

    }

    public String create() {
        setErrorMessage("");
        bookBean = new BookBean();
        setBookRelationEntity();
        return "book_form";
    }

    public void setBookRelationEntity() {
        categoryBean = new CategoryBean();
        authorBean = new AuthorBean();
        author = authorManager.findList();
        categories = categoryManager.findList();
    }

    public String save() {
        setInfoMessage("");
        authorBean = authorManager.get(selectedAuthor);
        categoryBean = categoryManager.get(selectedCategory);
        bookBean.setAuthor(authorBean);
        bookBean.setCategory(categoryBean);
        try {
            if (bookBean.getId() == null) {
                bookManager.add(bookBean);

                setInfoMessage("Book Added Successfully");
            } else {
                bookManager.edit(bookBean);
                setInfoMessage("Book Updated Successfully");
            }
            reloadBooks();
            setErrorMessage("");
            setCheckMessage(true);
            saveMessage();
            return "books";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            setCheckMessage(true);
            return "book_form";
        }

    }

    public String edit(Integer id) {
        try {

            bookBean = bookManager.get(id);
            setBookRelationEntity();
            return "book_form";
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            return "book_form";
        }
    }

    public void remove(Integer id) {
        setInfoMessage("");
        try {
            bookManager.remove(id);
            reloadBooks();
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
    public BookManager getBookManager() {
        return bookManager;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
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
    public List<CategoryBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryBean> categories) {
        this.categories = categories;
    }

    public List<AuthorBean> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorBean> author) {
        this.author = author;
    }

    public Integer getSelectedAuthor() {
        return selectedAuthor;
    }

    public void setSelectedAuthor(Integer selectedAuthor) {
        this.selectedAuthor = selectedAuthor;
    }

    public Integer getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Integer selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public AuthorBean getAuthorBean() {
        return authorBean;
    }

    public void setAuthorBean(AuthorBean authorBean) {
        this.authorBean = authorBean;
    }

    public CategoryBean getCategoryBean() {
        return categoryBean;
    }

    public void setCategoryBean(CategoryBean categoryBean) {
        this.categoryBean = categoryBean;
    }

    public BookBean getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(BookBean selectedBook) {
        this.selectedBook = selectedBook;
    }
   
}
