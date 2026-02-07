import controller.AuthenticationController;
import controller.CustomerController;
import controller.ProductController;
import repository.IOrderRepository;
import repository.IProductRepository;
import repository.IUserRepository;
import repository.json_implementation.JsonOrderRepository;
import repository.json_implementation.JsonProductRepository;
import repository.json_implementation.JsonUserRepository;
import services.AuthenticationService;
import services.CartServices;
import services.CustomerServices;
import services.OrderServices;
import services.ProductServices;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {


        // Repositories
        IUserRepository userRepo = new JsonUserRepository("data/json_files/Users.json");
        IProductRepository productRepo = new JsonProductRepository("data/json_files/Products.json");
        IOrderRepository orderRepo = new JsonOrderRepository("data/json_files/Orders.json");

        // Services
        CustomerServices customerService = new CustomerServices(userRepo);
        ProductServices productService = new ProductServices(productRepo);
        CartServices cartService = new CartServices(productService, customerService);
        AuthenticationService authService = new AuthenticationService(userRepo);

        OrderServices orderService = new OrderServices(
                orderRepo,
                productService,
                customerService
        );


        // Main Frame
        MainFrame mainFrame = new MainFrame();


        // Controllers:

            // Authentication
        new AuthenticationController(mainFrame, authService);

            // Admin product management controller
        new ProductController(mainFrame, productService);

            // Customer controller
        new CustomerController(mainFrame, customerService, productService, cartService, orderService);


        mainFrame.setVisible(true);
    }
}
