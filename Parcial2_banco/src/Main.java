import logica.Admin;
import logica.Cliente;
import logica.DatosDePrueba;
import logica.Validador;


import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        DatosDePrueba.cargar();

        while (true) {
            String[] opciones = { "Iniciar sesión", "Salir" };
            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Bienvenido al sistema bancario",
                    "Inicio",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (opcion == 1 || opcion == JOptionPane.CLOSED_OPTION) break;

            String email = Validador.validarEmail("Ingrese su email:", false);
            if (email == null) continue;

            String contrasenia = Validador.validarContrasenia();
            if (contrasenia == null) continue;

            for (Admin admin : Admin.getAdmins()) {
                if (admin.getEmail().equalsIgnoreCase(email) &&
                        admin.getContrasenia().equals(contrasenia)) {
                    admin.Login();
                    continue;
                }
            }

            for (Cliente cliente : Cliente.getClientes()) {
                if (cliente.getEmail().equalsIgnoreCase(email) &&
                        cliente.getContrasenia().equals(contrasenia)) {
                    cliente.Login();
                    continue;
                }
            }

            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
        }

        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
    }
}
