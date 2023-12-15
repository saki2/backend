package rs.ac.uns.ftn.informatika.jpa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptExample {

    public static void main(String[] args) {
        // Hash a password
        String password = "admin123";
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);

        // Check if a password matches the stored hash
        String enteredPassword = "$2a$10$xxZY6ssiuGbG/YwQ.3Uf..Ac5mS8zW8viMNsOcot7JLUYVUg6rqTm";
        boolean passwordMatches = checkPassword(enteredPassword, hashedPassword);
        System.out.println("Password Matches: " + passwordMatches);
    }

    private static String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private static boolean checkPassword(String enteredPassword, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, hashedPassword);
    }
}
