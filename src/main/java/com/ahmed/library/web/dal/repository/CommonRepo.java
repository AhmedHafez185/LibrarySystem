/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.dal.repository;


import java.util.List;
/**
 *
 * @author Ahmed Hafez
 * @param <E>
 */
public interface CommonRepo<E>{
    public E add(E entity);
    public E update(E entity);
    public void remove(Integer id) throws Exception;
    public E findById(Integer id);
    public List<E> findList() ;
}
