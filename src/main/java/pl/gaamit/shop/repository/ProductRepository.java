package pl.gaamit.shop.repository;

import pl.gaamit.shop.controller.ProductNotFoundException;
import pl.gaamit.shop.model.Product;

import java.util.List;

public interface ProductRepository {

    Product findProductByName(String name) throws ProductNotFoundException;
    List<Product> findAll();
    void remove(Product product) throws ProductNotFoundException;
    void removeProductByName(String name) throws ProductNotFoundException;
    void add(Product product);
}
