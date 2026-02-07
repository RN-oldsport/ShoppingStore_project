package common;
import java.security.MessageDigest;

public class PasswordHasher {

    public static String hasher(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] inputBytes = password.getBytes();
            byte[] hashedBytes = md.digest(inputBytes);

            StringBuilder sb = new StringBuilder();
            for(byte b : hashedBytes){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
        catch(Exception e){
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
