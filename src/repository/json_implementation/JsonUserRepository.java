package repository.json_implementation;

import com.google.gson.*;
import model.Admin;
import model.Customer;
import model.Role;
import model.User;
import repository.IUserRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonUserRepository implements IUserRepository {

    private final String filePath;
    private final Gson gson;

    public JsonUserRepository(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public List<User> loadUsers() {

        try (FileReader reader = new FileReader(filePath)) {

            JsonElement element = JsonParser.parseReader(reader);

            if (element == null || element.isJsonNull())
                return new ArrayList<>();

            JsonArray array = element.getAsJsonArray();

            List<User> users = new ArrayList<>();

            for (JsonElement e : array) {
                JsonObject obj = e.getAsJsonObject();

                Role role = Role.valueOf(obj.get("role").getAsString());

                if (role == Role.ADMIN) {
                    users.add(gson.fromJson(obj, Admin.class));
                } else if (role == Role.CUSTOMER) {
                    users.add(gson.fromJson(obj, Customer.class));
                }
            }

            return users;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean saveUsers(List<User> users) {

        try (FileWriter writer = new FileWriter(filePath)) {

            gson.toJson(users, writer);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
