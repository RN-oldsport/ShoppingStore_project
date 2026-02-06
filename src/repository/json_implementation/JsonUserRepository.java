//package Repository.JsonImp; // پکیج را متناسب با ساختار خود تنظیم کنید
//
//import com.google.gson.*;
//import Model.Admin;
//import Model.Customer;
//import Model.User;
//import Repository.IUserRepository;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JsonUserRepository implements IUserRepository {
//
//    private final String filePath;
//    private final Gson gson;
//
//    public JsonUserRepository(String filePath) {
//        this.filePath = filePath;
//        // فعال کردن PrettyPrinting برای خوانایی فایل جیسون در صورت باز کردن دستی
//        this.gson = new GsonBuilder().setPrettyPrinting().create();
//    }
//
//    // ------------------- LOAD & SAVE (The Core) -------------------
//
//    @Override
//    public List<User> load() {
//        List<User> users = new ArrayList<>();
//        File file = new File(filePath);
//
//        if (!file.exists()) {
//            return users;
//        }
//
//        try (Reader reader = new FileReader(file)) {
//            JsonElement fileElement = JsonParser.parseReader(reader);
//
//            if (fileElement == null || fileElement.isJsonNull() || !fileElement.isJsonArray()) {
//                return users;
//            }
//
//            JsonArray jsonArray = fileElement.getAsJsonArray();
//
//            for (JsonElement element : jsonArray) {
//                JsonObject jsonObject = element.getAsJsonObject();
//
//                // تشخیص هوشمند نوع کلاس بر اساس فیلد type
//                if (jsonObject.has("type")) {
//                    String type = jsonObject.get("type").getAsString();
//                    if (type.equalsIgnoreCase("ADMIN")) {
//                        users.add(gson.fromJson(jsonObject, Admin.class));
//                    } else if (type.equalsIgnoreCase("CUSTOMER")) {
//                        users.add(gson.fromJson(jsonObject, Customer.class));
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }
//
//    @Override
//    public void save(List<User> users) {
//        try (Writer writer = new FileWriter(filePath)) {
//            gson.toJson(users, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // ------------------- FINDERS -------------------
//
//    @Override
//    public User findById(int id) {
//        List<User> users = load();
//        // جستجو در لیست برای پیدا کردن آیدی مشابه
//        for (User user : users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        List<User> users = load();
//        for (User user : users) {
//            if (user.getUsername().equalsIgnoreCase(username)) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    // ------------------- MODIFIERS -------------------
//
//    @Override
//    public void add(User user) {
//        List<User> users = load();
//
//        // تولید خودکار ID (Auto-Increment)
//        // چون دیتابیس واقعی نداریم، باید خودمان ID بعدی را محاسبه کنیم
//        int maxId = 0;
//        for (User u : users) {
//            if (u.getId() > maxId) {
//                maxId = u.getId();
//            }
//        }
//        user.setId(maxId + 1);
//
//        users.add(user);
//        save(users); // ذخیره مجدد کل لیست
//    }
//
//    @Override
//    public void update(User user) {
//        List<User> users = load();
//        boolean found = false;
//
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).getId() == user.getId()) {
//                // جایگزین کردن آبجکت جدید با قبلی
//                users.set(i, user);
//                found = true;
//                break;
//            }
//        }
//
//        if (found) {
//            save(users);
//        } else {
//            System.err.println("User with ID " + user.getId() + " not found for update.");
//        }
//    }
//
//    @Override
//    public void delete(int id) {
//        List<User> users = load();
//
//        // حذف کردن اگر آیدی مطابقت داشت
//        boolean removed = users.removeIf(u -> u.getId() == id);
//
//        if (removed) {
//            save(users);
//        } else {
//            System.err.println("User with ID " + id + " not found for deletion.");
//        }
//    }
//}