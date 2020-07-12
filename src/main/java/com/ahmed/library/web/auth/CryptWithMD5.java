/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ahmed Hafez
 */
public class CryptWithMD5 {
    private static MessageDigest md;

   public static String encryptWithMD5(String pass){
    try {
        md = MessageDigest.getInstance("MD5");
        byte[] passBytes = pass.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
        ex.printStackTrace();
    }
        return null;
   }
   
   public static void main(String [] args){
       System.out.println(encryptWithMD5("Ahmed"));
   }
}
