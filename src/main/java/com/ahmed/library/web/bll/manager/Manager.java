/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.manager;

import java.util.List;

/**
 *
 * @author Ahmed Hafez
 */
public interface Manager<E> {

    /**
     *
     * @param bean
     * @return
     */
    public E add(E bean) throws Exception;
    public List<E> findList();
    public E edit(E bean) throws Exception;
    public void remove(int cityId)  throws Exception;
    public E get(Integer id);
}
