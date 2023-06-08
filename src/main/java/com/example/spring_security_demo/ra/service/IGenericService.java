package com.example.spring_security_demo.ra.service;

import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface IGenericService<T,E> {
    List<T> findAll();
    boolean deleteById(E id);
    boolean save(T t);
    T findById(E id);
}
