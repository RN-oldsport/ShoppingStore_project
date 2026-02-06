import controller.AuthenticationController;
import controller.CustomerController;
import controller.ProductController;
import repository.IProductRepository;
import repository.IUserRepository;
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

        // -----------------------------
        // 1️⃣ Repositories
        // -----------------------------
        IUserRepository userRepo = new JsonUserRepository("data/json_files/Users.json");
        IProductRepository productRepo = new JsonProductRepository("data/json_files/Products.json");

        // -----------------------------
        // 2️⃣ Services
        // -----------------------------
        CustomerServices customerService = new CustomerServices(userRepo);
        ProductServices productService = new ProductServices(productRepo);
        CartServices cartService = new CartServices(productService);
        AuthenticationService authService = new AuthenticationService(userRepo, customerService);

        OrderServices orderService = new OrderServices(
                null, // بعداً IOrderRepository اضافه می‌کنیم، فعلاً می‌تونی null بذاری یا پیاده کن
                productService,
                customerService
        );

        // -----------------------------
        // 3️⃣ Main Frame
        // -----------------------------
        MainFrame mainFrame = new MainFrame();

        // -----------------------------
        // 4️⃣ Controllers
        // -----------------------------
        // Authentication
        new AuthenticationController(mainFrame, authService);

        // Admin product management controller
        new ProductController(mainFrame, productService);

        // Customer controller (بعد از login فعال می‌شود)
        new CustomerController(mainFrame, customerService, productService, cartService, orderService);

        // -----------------------------
        // 5️⃣ Show GUI (Auth panel by default)
        // -----------------------------
        mainFrame.setVisible(true);
    }
}
