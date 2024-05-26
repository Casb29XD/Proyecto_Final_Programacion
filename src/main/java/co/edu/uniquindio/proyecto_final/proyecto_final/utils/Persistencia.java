package co.edu.uniquindio.proyecto_final.proyecto_final.utils;

import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.*;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    public static final String RUTA_ARCHIVO_EMPLEADOS = "src/main/resources/persistencia/archivoEmpleados.txt";
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_EVENTOS = "src/main/resources/persistencia/archivoEventos.txt";
    public static final String RUTA_ARCHIVO_RESERVACIONES = "/src/main/resources/persistencia/archivoReservas.txt";
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/log/BancoLog.txt";
    public static final String RUTA_ARCHIVO_OBJETOS = "co.edu.uniquindio.programacion3/src/main/resources/persistencia/archivoObjetos.txt";
    public static final String RUTA_ARCHIVO_MODELO_BANCO_BINARIO = "src/main/resources/persistencia/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_BANCO_XML = "src/main/resources/persistencia/model.xml";


    public static void cargarDatosArchivos(Sgre sgre) throws FileNotFoundException, IOException {
        //cargar archivo de clientes
       /* ArrayList<Cliente> clientesCargados = cargarClientes();
        if(clientesCargados.size() > 0)
            banco.getListaClientes().addAll(clientesCargados);
*/
        //cargar archivos empleados
        List<Empleado> empleadosCargados = cargarEmpleados();
        if(empleadosCargados.size() > 0)
            sgre.getEmpleados().addAll(empleadosCargados);

        //cargar archivo transcciones

        //cargar archivo empleados

        //cargar archivo prestamo

    }


    ////crud Empleados
    public static void guardarEmpleados(List<Empleado> listaEmpleados) throws IOException {
        FileWriter fw = new FileWriter(RUTA_ARCHIVO_EMPLEADOS);
        BufferedWriter bfw = new BufferedWriter(fw);
        for (Empleado empleado : listaEmpleados) {
            bfw.write(empleado.getId() + "@@" + empleado.getNombre() + "@@" + empleado.getCorreo()+ "\n");
        }
        bfw.close();
        fw.close();
    }

    public static List<Empleado> cargarEmpleados() throws IOException {
        List<Empleado> empleados = new ArrayList<>();
        FileReader fr = new FileReader(RUTA_ARCHIVO_EMPLEADOS);
        BufferedReader bfr = new BufferedReader(fr);
        String linea;
        while ((linea = bfr.readLine()) != null) {
            String[] partes = linea.split("@@");
            if (partes.length >= 3) {
                Empleado empleado = new Empleado();
                empleado.setId(partes[0]);
                empleado.setNombre(partes[1]);
                empleado.setCorreo(partes[2]);
                empleados.add(empleado);
            }
        }
        bfr.close();
        fr.close();
        return empleados;
    }

    //crud usuarios
    public static void guardarUsuarios(List<Usuario> listUsuarios) throws IOException {
        FileWriter fw = new FileWriter(RUTA_ARCHIVO_USUARIOS);
        BufferedWriter bfw = new BufferedWriter(fw);
        for (Usuario usuario : listUsuarios) {
            bfw.write(usuario.getId() + "@@" + usuario.getNombre() + "@@" + usuario.getCorreo() + "\n");
        }
        bfw.close();
        fw.close();
    }

    public static List<Usuario> cargarUsuarios() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        FileReader fr = new FileReader(RUTA_ARCHIVO_USUARIOS);
        BufferedReader bfr = new BufferedReader(fr);
        String linea;
        while ((linea = bfr.readLine()) != null) {
            String[] partes = linea.split("@@");
            if (partes.length >= 3) {
                Usuario usuario = new Usuario();
                usuario.setId(partes[0]);
                usuario.setNombre(partes[1]);
                usuario.setCorreo(partes[2]);
                usuarios.add(usuario);
            }
        }
        bfr.close();
        fr.close();
        return usuarios;
    }
    //crud Eventos
    public static void guardarEventos(List<Evento> listEventos) throws IOException {
        FileWriter fw = new FileWriter(RUTA_ARCHIVO_EVENTOS);
        BufferedWriter bfw = new BufferedWriter(fw);
        for (Evento evento : listEventos) {
            bfw.write(evento.getId() + "@@" + evento.getNombre() + "@@" + evento.getDescripcion()
            +"@@" + evento.getFecha() + "@@"+ evento.getCapacidadMaxima() +"@@" +evento.getEmpleado()
                    +"@@"+evento.getReserva()+ "\n");
        }
        bfw.close();
        fw.close();
    }

    public static List<Evento> cargarEvento() throws IOException {
        List<Evento> eventos = new ArrayList<>();
        FileReader fr = new FileReader(RUTA_ARCHIVO_EVENTOS);
        BufferedReader bfr = new BufferedReader(fr);
        String linea;
        while ((linea = bfr.readLine()) != null) {
            String[] partes = linea.split("@@");
            if (partes.length >= 3) {
                Evento evento = new Evento();
                evento.setId(partes[0]);
                evento.setNombre(partes[1]);
                evento.setDescripcion(partes[2]);
                evento.setFecha(partes[3]);
                evento.setCapacidadMaxima(partes[4]);
                eventos.add(evento);
            }
        }
        bfr.close();
        fr.close();
        return eventos;
    }
    //crud Reservas
    public static void guardarReservas(List<Reserva> listReserva) throws IOException {
        FileWriter fw = new FileWriter(RUTA_ARCHIVO_RESERVACIONES);
        BufferedWriter bfw = new BufferedWriter(fw);
        for (Reserva reserva : listReserva) {
            bfw.write(reserva.getId() + "@@" + reserva.getUsuario() +"@@"+ reserva.getEvento()
                    +"@@" + reserva.getFechaSolicitud() +"@@"+reserva.getEstado()+ "\n");
        }
        bfw.close();
        fw.close();
    }

    public static List<Reserva> cargaReservas() throws IOException {
        List<Reserva> reservas = new ArrayList<>();
        FileReader fr = new FileReader(RUTA_ARCHIVO_RESERVACIONES);
        BufferedReader bfr = new BufferedReader(fr);
        String linea;
        while ((linea = bfr.readLine()) != null) {
            String[] partes = linea.split("@@");
            if (partes.length >= 4) {
                Reserva reserva = new Reserva();
                reserva.setId(partes[0]);
                reserva.setUsuario(partes[1]);
                reserva.setEvento(partes[2]);
                reserva.setFechaSolicitud(partes[3]);
                reserva.setEstado(partes[4]);
                reservas.add(reserva);
            }
        }
        bfr.close();
        fr.close();
        return reservas;
    }

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    //------------------------------------SERIALIZACIÓN  y XML

    public static Sgre cargarRecursoSgreBinario() {
        Sgre sgre = null;
        try {
            sgre = (Sgre) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_BANCO_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sgre;
    }

    public static void guardarRecursoSgreBinario(Sgre sgre) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO_MODELO_BANCO_BINARIO));
            oos.writeObject(sgre);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Sgre cargarRecursosSgreXML() {
        Sgre sgre = null;
        try {
            sgre = (Sgre) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_BANCO_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sgre;
    }

    public static void guardarRecursoSgreXML(Sgre sgre) {
        try {
            XMLEncoder codificadorXML = new XMLEncoder(new FileOutputStream(RUTA_ARCHIVO_MODELO_BANCO_XML));
            codificadorXML.writeObject(sgre);
            codificadorXML.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveObservableListToCSV(ObservableList<ReservaDto> data, Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showSaveDialog(primaryStage);

        if (selectedFile != null) {
            try (FileWriter writer = new FileWriter(selectedFile)) {
                // Write header
                writer.append("Id,Usuario,Evento,Fecha,Estado\n");

                // Write data
                for (ReservaDto reserva : data) {
                    writer.append(reserva.id())
                            .append(',')
                            .append(reserva.usuario())
                            .append(',')
                            .append(reserva.evento())
                            .append(',')
                            .append(reserva.fechaSolicitud())
                            .append(',')
                            .append(reserva.estado())
                            .append('\n');
                }
                System.out.println("CSV file was saved successfully!");
            } catch (IOException e) {
                System.err.println("An error occurred while saving the CSV file: " + e.getMessage());
            }
        }
    }
}
