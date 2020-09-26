package com.dmodi.spring.main;

import com.dmodi.spring.model.Product;
import com.dmodi.spring.service.ProductService;
import java.util.Arrays;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
public class SpringOrmMain {
    public static void main(String[] args) {
        //Create Spring application context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
        //Get service from context. (service's dependency (ProductDAO) is autowired in ProductService)
        ProductService productService = ctx.getBean(ProductService.class);
        productService.add(new Product(1, "Bulbs"));
        productService.add(new Product(2, "Tubelights"));
        
        System.out.println("\nListing All Products using JPA: " + productService.listAll());
        
        try {
            //Either all below products will be persisted or not any one.
            productService.addAll(Arrays.asList(
                    new Product(3, "Books"),
                    new Product(4, "Soaps"),
                    new Product(5, "Computers"))); //Change this Id to 1 (duplicate), no Product will be persisted.
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        System.out.println("\nListing All Products using JPA: " + productService.listAll());
        ctx.close();
    }
}
