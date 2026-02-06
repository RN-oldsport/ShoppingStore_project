package repository.json_implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Order;
import repository.IOrderRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonOrderRepository implements IOrderRepository {

    private final String filePath;
    private final Gson gson;

    public JsonOrderRepository(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public List<Order> findAll() {

        try (FileReader reader = new FileReader(filePath)) {

            Type listType = new TypeToken<List<Order>>() {}.getType();
            List<Order> orders = gson.fromJson(reader, listType);

            if (orders == null)
                return new ArrayList<>();

            return orders;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void saveAll(List<Order> orders) {

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(orders, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findById(int id) {

        List<Order> orders = findAll();

        for (Order order : orders) {
            if (order.getId() == id)
                return order;
        }

        return null;
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {

        List<Order> orders = findAll();
        List<Order> result = new ArrayList<>();

        for (Order order : orders) {
            if (order.getCustomerId() == customerId)
                result.add(order);
        }

        return result;
    }
}
