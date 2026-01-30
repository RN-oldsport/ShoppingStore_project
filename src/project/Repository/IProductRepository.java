package project.Repository;

import project.Model.Product;

import java.util.ArrayList;

public interface IProductRepository {

    ArrayList<Product> findAll();

    Product findById(int id);

    void saveAll(ArrayList<Product> products);

}
