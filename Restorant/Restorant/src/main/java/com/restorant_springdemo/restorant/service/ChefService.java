package com.restorant_springdemo.restorant.service;

import com.restorant_springdemo.restorant.dto.ChefDto;
import jakarta.transaction.SystemException;

import java.util.List;

public interface ChefService {


    List<ChefDto>getAllChef();

    ChefDto getChefById(Long id) throws SystemException;

    /*
     تضيف Chef واحدة جديدة.
    */
    ChefDto saveChef(ChefDto chefDto) throws SystemException;

    /*
     تعدّل Chef واحدة موجودة.

     ChefDto لازم تحتوي على id.
    */
    ChefDto updateChef(ChefDto chefDto) throws SystemException;

    /*
     تحذف Chef واحدة باستخدام id.
    */
    void deleteChefById(Long id) throws SystemException;
}
