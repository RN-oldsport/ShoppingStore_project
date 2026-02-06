package repository.json_implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import repository.IProductRepository;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JsonProductRepository implements IProductRepository {

    private final String filePath;
    private final Gson gson;

    public JsonProductRepository(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }


    @Override
    public List<Product> findAll() {
        try (FileReader reader = new FileReader("data/json_files/Products.json")) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Product>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public void saveAll(List<Product> products) {

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Product findById(int id) {

        List<Product> products = findAll();

        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}




