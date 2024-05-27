package co.edu.uniquindio.proyecto_final.proyecto_final.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Login {
    public boolean login(String username, String password, String file) {
        Map<String, String> users = loadUsers(file);

        boolean success = authenticate(users, username, password);
        if (success) {
            System.out.println("Login Successful");
        } else {
            mostrarMensajeConfirmacion("Usuario o Contraseña INCORRECTO.");
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
            bw.write(username + "," + password + "\n");
            bw.close();
        } catch (IOException e) {
            System.err.println("Error writing to users file: " + e.getMessage());
            return false;
        }
        return true;
    }
    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    public boolean updatePassword(String username, String newPassword, String file) {
        Map<String, String> users = loadUsers(file);

        if (!users.containsKey(username)) {
            System.out.println("Usuario no encontrado");
            return false;
        }

        users.put(username, newPassword);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to users file: " + e.getMessage());
            return false;
        }

        System.out.println("Contraseña actualizada exitosamente para el usuario: " + username);
        return true;
    }
    public boolean deleteUser(String username, String file) {
        Map<String, String> users = loadUsers(file);

        if (!users.containsKey(username)) {
            System.out.println("Usuario no encontrado");
            return false;
        }

        users.remove(username);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to users file: " + e.getMessage());
            return false;
        }

        System.out.println("Usuario eliminado exitosamente: " + username);
        return true;
    }

}
