/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.manager.impl;

import com.ahmed.library.web.bll.beans.BookBean;
import com.ahmed.library.web.bll.manager.Manager;
import com.ahmed.library.web.bll.transformer.BookTransformer;
import com.ahmed.library.web.dal.entity.Book;
import com.ahmed.library.web.dal.repository.impl.BookRepo;
import java.util.List;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.*;
import java.util.ArrayList;

/**
 *
 * @book Ahmed Hafez
 */
public class BookManager implements Manager<BookBean> {

    private BookTransformer bookTransformer = new BookTransformer();
    private BookRepo bookRepo = new BookRepo();
    @Override
    public BookBean add(BookBean bean) throws Exception {
        try {
            validateBook(bean);
            openSession();
            Book book = bookTransformer.transformBeanToEntity(bean);
            beginTransaction();
            Book bookEntity = bookRepo.add(book);
            BookBean resultBean = bookTransformer.transformEntityToBean(bookEntity);
            commitTransaction();
            return resultBean;
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<BookBean> findList() {
        openSession();
        beginTransaction();
        List<Book> booksList = bookRepo.findList();
        List<BookBean> resultBookBean = new ArrayList<>();
        for (Book book : booksList) {
            BookBean bookBean = bookTransformer.transformEntityToBean(book);
            resultBookBean.add(bookBean);
        }
        commitTransaction();
        return resultBookBean;
    }

    @Override
    public BookBean edit(BookBean bean) throws Exception {
        try {
            validateBook(bean);
            openSession();
            Book book = bookTransformer.transformBeanToEntityWithId(bean);
            beginTransaction();
            Book bookEntity = bookRepo.update(book);
            BookBean resultBean = bookTransformer.transformEntityToBean(bookEntity);
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
            bookRepo.remove(id);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }

    }

    @Override
    public BookBean get(Integer id) {
        openSession();
        beginTransaction();
        Book bookEntity = bookRepo.findById(id);
        BookBean resultBean = bookTransformer.transformEntityToBean(bookEntity);
        commitTransaction();
        return resultBean;
    }

    private void validateBook(BookBean book) throws Exception {
        if (null == book) {
            throw new Exception("The Book Bean is null");
        }
        if (null == book.getName() || book.getName().isEmpty()) {
            throw new Exception("Book Name should not to be Empty or Null");
        }
       
    }

    public int getByName(String name) {
        openSession();
        beginTransaction();
        int size = bookRepo.findByName(name);
        commitTransaction();
        return size;
    }
}
