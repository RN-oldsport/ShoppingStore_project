package di;

import model.Product;
import repository.IProductRepository;
import repository.json_implementation.JsonProductRepository;
import services.ProductServices;

public class DependencyContainer {

    IProductRepository productRepo = new JsonProductRepository(
            "../../resources/json_files/Products.json");

    ProductServices productServices = new ProductServices(productRepo);
}
