package com.dmodi.spring.service;

import com.dmodi.spring.dao.ProductDao;
import com.dmodi.spring.model.Product;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Transactional
    public void add(Product product) {
        productDao.persist(product); //insert into Product (name, id) values (?, ?)
    }
    @Transactional
    public void addAll(Collection<Product> products) {
        for (Product product : products) {
            productDao.persist(product); //insert into Product (name, id) values (?, ?)
        }
    }
    @Transactional(readOnly = true)
    public List<Product> listAll() {
        return productDao.findAll(); //select product0_.id as id0_, product0_.name as name0_ from Product product0_
    }
}
