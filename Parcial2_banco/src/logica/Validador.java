package logica;

import javax.swing.*;

public class Validador {
    public static boolean textoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean emailValido(String email) {
        return textoValido(email) && email.contains("@") && email.contains(".");
    }

    public static boolean emailExisteEnClientes(String email) {
        for (Cliente c : Cliente.clientes) {
            if (c.getMail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contraseniaValida(String pass) {
        return textoValido(pass) && pass.length() >= 4;
    }

    public static String validarNombre() {
        while (true) {
            String nombre = JOptionPane.showInputDialog("Ingrese nombre:");
            if (nombre == null) return null;
            if (!textoValido(nombre)) {
                JOptionPane.showMessageDialog(null, "Nombre inválido.");
            } else {
                return nombre;
            }
        }
    }

    public static String validarEmail(boolean verificarDuplicado) {
        while (true) {
            String email = JOptionPane.showInputDialog("Ingrese email:");
            if (email == null) return null;
            if (!emailValido(email)) {
                JOptionPane.showMessageDialog(null, "Email inválido.");
            } else if (verificarDuplicado && emailExisteEnClientes(email)) {
                JOptionPane.showMessageDialog(null, "Ese email ya está registrado.");
            } else {
                return email;
            }
        }
    }

    public static String validarContrasenia() {
        while (true) {
            String contrasenia = JOptionPane.showInputDialog("Ingrese contraseña:");
            if (contrasenia == null) return null;
            if (!contraseniaValida(contrasenia)) {
                JOptionPane.showMessageDialog(null, "Contraseña inválida (mínimo 4 caracteres).");
            } else {
                return contrasenia;
            }
        }
    }
}
