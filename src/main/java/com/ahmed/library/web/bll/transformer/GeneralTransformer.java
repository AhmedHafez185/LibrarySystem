/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.bll.transformer;

/**
 *
 * @author Ahmed Hafez
 */
public interface GeneralTransformer<E,B>{
    public B transformEntityToBean(E entity);
    public E transformBeanToEntity(B bean);
     public E transformBeanToEntityWithId(B bean);
    
}
