package co.edu.uniquindio.proyecto_final.proyecto_final;

import co.edu.uniquindio.proyecto_final.proyecto_final.utils.Login;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.Persistencia;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;

public class main {

    public static void main(String[] args) {
        Login login = new Login();
        //login.register("abc","abc","src/main/resources/persistencia/login/Usuarios.txt");
        login.login("abc","abc","src/main/resources/persistencia/login/Usuarios.txt");
    }
}
