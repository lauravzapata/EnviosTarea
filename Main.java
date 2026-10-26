package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Envio> listaEnvios = new ArrayList<>();
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "SISTEMA DE ENVIOS\n\n"
                            + "1. Registrar envio\n"
                            + "2. Buscar envio\n"
                            + "3. Listar envios\n"
                            + "4. Editar envio\n"
                            + "5. Eliminar envio\n"
                            + "6. Calcular costo\n"
                            + "7. Salir\n\n"
                            + "Ingrese una opcion:"));

            switch (opcion) {
                case 1:
                    registrarEnvio(listaEnvios);
                    break;

                case 2:
                    buscarEnvio(listaEnvios);
                    break;

                case 3:
                    listarEnvios(listaEnvios);
                    break;

                case 4:
                    editarEnvio(listaEnvios);
                    break;

                case 5:
                    eliminarEnvio(listaEnvios);
                    break;

                case 6:
                    calcularCosto(listaEnvios);
                    break;

                case 7:
                    JOptionPane.showMessageDialog(null, "Hasta pronto");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
            }

        } while (opcion != 7);
    }
    public static void registrarEnvio(ArrayList<Envio> listaEnvios) {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo");
        if (buscarPorCodigo(listaEnvios, codigo) != null) {
            JOptionPane.showMessageDialog(null, "Ese codigo ya existe");
            return;
        }
        String destinatario = JOptionPane.showInputDialog("Ingrese el destinatario");
        double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso en kg"));
        String tipo = JOptionPane.showInputDialog(
                "Seleccione el tipo:\n"
                        + "1. Nacional\n"
                        + "2. Internacional\n"
                        + "3. Express");

        Envio envio = null;
        if (tipo.equals("1")) {
            String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad");
            String respuesta = JOptionPane.showInputDialog("¿Tiene servicio a domicilio? Escriba si o no");
            boolean domicilio = respuesta.equalsIgnoreCase("si");
            envio = new EnvioNacional(codigo, destinatario, peso, ciudad, domicilio);

        } else if (tipo.equals("2")) {

            String pais = JOptionPane.showInputDialog("Ingrese el pais");
            String respuesta = JOptionPane.showInputDialog("¿Tiene seguro? Escriba si o no");
            boolean seguro = respuesta.equalsIgnoreCase("si");
            envio = new EnvioInternacional(codigo, destinatario, peso, pais, seguro);

        } else if (tipo.equals("3")) {
            String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad");
            String respuesta = JOptionPane.showInputDialog("¿Tiene servicio a domicilio? Escriba si o no");
            boolean domicilio = respuesta.equalsIgnoreCase("si");
            EnvioExpress express = new EnvioExpress(codigo, destinatario, peso, ciudad, domicilio);

            byte horas = Byte.parseByte(JOptionPane.showInputDialog("Ingrese las horas maximas de entrega"));
            express.setCantidadHora(horas);
            envio = express;

        } else {
            JOptionPane.showMessageDialog(null, "Tipo de envio no valido");
        }
        if (envio != null) {
            listaEnvios.add(envio);
            JOptionPane.showMessageDialog(null, "Envio registrado correctamente");
        }
    }

    public static Envio buscarPorCodigo(ArrayList<Envio> listaEnvios, String codigo) {
        for (Envio envio : listaEnvios) {
            if (envio.getCodigo().equalsIgnoreCase(codigo)) {
                return envio;
            }
        }
        return null;
    }

    public static void buscarEnvio(ArrayList<Envio> listaEnvios) {

        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del envio");

        Envio envio = buscarPorCodigo(listaEnvios, codigo);

        if (envio == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el envio");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Codigo: " + envio.getCodigo()
                            + "\nDestinatario: " + envio.getDestinatario()
                            + "\nPeso: " + envio.getPeso() + " kg"
                            + "\nCosto: $" + envio.costoEnvio()
            );
        }
    }

    public static void listarEnvios(ArrayList<Envio> listaEnvios) {
        if (listaEnvios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay envios registrados");
        } else {
            String informacion = "";
            for (Envio envio : listaEnvios) {
                informacion += "Codigo: " + envio.getCodigo()
                        + "\nDestinatario: " + envio.getDestinatario()
                        + "\nPeso: " + envio.getPeso() + " kg"
                        + "\nCosto: $" + envio.costoEnvio()
                        + "\n-------------------------\n";
            }
            JOptionPane.showMessageDialog(null, informacion);
        }
    }

    public static void editarEnvio(ArrayList<Envio> listaEnvios) {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del envio");

        Envio envio = buscarPorCodigo(listaEnvios, codigo);

        if (envio == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el envio");
            return;
        }

        String destinatario = JOptionPane.showInputDialog("Ingrese el nuevo destinatario");
        double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo peso"));

        envio.setDestinatario(destinatario);
        envio.setPeso(peso);

        if (envio instanceof EnvioExpress) {

            EnvioExpress express = (EnvioExpress) envio;

            express.setCiudad(JOptionPane.showInputDialog("Ingrese la nueva ciudad"));

            String respuesta = JOptionPane.showInputDialog("¿Tiene servicio a domicilio? Escriba si o no");
            boolean domicilio = respuesta.equalsIgnoreCase("si");
            express.setDomicilio(domicilio);

            byte horas = Byte.parseByte(JOptionPane.showInputDialog("Ingrese las horas de entrega"));

            express.setCantidadHora(horas);

        } else if (envio instanceof EnvioNacional) {

            EnvioNacional nacional = (EnvioNacional) envio;
            nacional.setCiudad(JOptionPane.showInputDialog("Ingrese la nueva ciudad"));

            String respuesta = JOptionPane.showInputDialog("¿Tiene servicio a domicilio? Escriba si o no");

            boolean domicilio = respuesta.equalsIgnoreCase("si");

            nacional.setDomicilio(domicilio);

        } else if (envio instanceof EnvioInternacional) {

            EnvioInternacional internacional = (EnvioInternacional) envio;

            internacional.setPais(JOptionPane.showInputDialog("Ingrese el nuevo pais"));

            String respuesta = JOptionPane.showInputDialog("¿Tiene seguro? Escriba si o no");
            boolean seguro = respuesta.equalsIgnoreCase("si");

            internacional.setSeguro(seguro);
        }

        JOptionPane.showMessageDialog(null, "Envio editado correctamente");
    }

    public static void eliminarEnvio(ArrayList<Envio> listaEnvios) {

        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del envio");

        Envio envio = buscarPorCodigo(listaEnvios, codigo);

        if (envio == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el envio");
        } else {
            listaEnvios.remove(envio);
            JOptionPane.showMessageDialog(null, "Envio eliminado correctamente");
        }
    }

    public static void calcularCosto(ArrayList<Envio> listaEnvios) {

        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del envio");

        Envio envio = buscarPorCodigo(listaEnvios, codigo);

        if (envio == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el envio");
        } else {
            JOptionPane.showMessageDialog(null, "El costo del envio es: $" + envio.costoEnvio());
        }
    }
}



