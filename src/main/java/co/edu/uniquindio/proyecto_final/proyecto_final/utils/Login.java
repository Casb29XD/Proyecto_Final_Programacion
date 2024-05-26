package co.edu.uniquindio.proyecto_final.proyecto_final.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Login {
    public boolean login(String username, String password, String file) {
        Map<String, String> users = loadUsers(file);

        boolean success = authenticate(users, username, password);
        if (success) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login failed!");
        }
        return success;
    }

    public void register(String username, String password, String file) {

        if (addNewUser(username, password,file)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists!");
        }
    }

    public Map<String, String> loadUsers(String filePath) {
        Map<String, String> users = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users file: " + e.getMessage());
        }
        return users;
    }

    public boolean authenticate(Map<String, String> users, String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public boolean addNewUser(String username, String password, String file) {
        Map<String, String> users = loadUsers(file);

        if (users.containsKey(username)) {
            return false; // El usuario ya existe
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(username + "," + password);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to users file: " + e.getMessage());
            return false;
        }
        return true;
    }
}
