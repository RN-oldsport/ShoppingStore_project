package services;

import model.Product;
import common.OperationResult;
import repository.IProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductServices {

    private final IProductRepository repo;
    private List<Product> products;

    public ProductServices(IProductRepository repo){
        this.repo = repo;
        this.products = repo.findAll();
    }



    public List<Product> getProducts(){
        return new ArrayList<>(products);
    }


    public OperationResult<Void> newProduct(String name, String category,
                                            int price, int stockQuantity, String description, String image) {
        int id = products.stream()
                .mapToInt(p -> p.getId())
                .max()
                .orElse(0) + 1;

        Product newProduct = new Product(id, name, category, price, stockQuantity, description, image);
        products.add(newProduct);
        repo.saveAll(products);
        return new OperationResult<>(true, "Successfully added product");
    }


    public OperationResult<Void> deleteProduct(int id) {
        Product product = findById(id);
        products.remove(product);
        repo.saveAll(products);
        return new OperationResult<>(true, "Successfully deleted product");
    }


    private Product findById(int id) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        return product;
    }


    public OperationResult<Void> modifyProductName(int id, String newName) {
        Product product = findById(id);
        if (product == null) {
            return new OperationResult<>(false, "No such product with id " + id);
        }
        product.setName(newName);
        repo.saveAll(products);
        return new OperationResult<>(true, "Successfully updated product with name " + newName);
    }


    public OperationResult<Void> modifyProductCategory(int id, String newCategory) {
        Product product = findById(id);
        if (product == null) {
            return new OperationResult<>(false, "No such product with id " + id);
        }
        product.setCategory(newCategory);
        repo.saveAll(products);
        return new OperationResult<>(true, "Successfully updated product category to " + newCategory);
    }


    public OperationResult<Void> modifyProductPrice(int id, int newPrice) {
        Product product = findById(id);
        if (product == null) {
            return new OperationResult<>(false, "No such product with id " + id);
        }
        product.setPrice(newPrice);
        repo.saveAll(products);
        return new OperationResult<>(true, "Successfully updated product price to " + newPrice);
    }


    public OperationResult<Void> modifyProductStockQuantity(int id, int newStockQuantity) {
        Product product = findById(id);
        if (product == null) {
            return new OperationResult<>(false, "No such product with id " + id);
        }
        product.setStockQuantity(newStockQuantity);
        repo.saveAll(products);
        return new OperationResult<>(true, "Successfully updated product stock quantity to " + newStockQuantity);
    }


    public OperationResult<Void> modifyProductDescription(int id, String newDescription) {
        Product product = findById(id);
        if (product == null) {
            return new OperationResult<>(false, "No such product with id " + id);
        }
        product.setDescription(newDescription);
        repo.saveAll(products);
        return new OperationResult<>(true, "Successfully updated product description");
    }


    public OperationResult<Void> modifyProductImage(int id, String newImage) {
        Product product = findById(id);
        if (product == null) {
            return new OperationResult<>(false, "No such product with id " + id);
        }
        product.setImage(newImage);
        repo.saveAll(products);
        return new OperationResult<>(true, "Successfully updated product image");
    }


}
