import logica.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Banco banco = new Banco();
        DatosDePrueba.cargar(banco);

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

            String email = Validador.validarEmail("Ingrese su email:", false, banco);
            if (email == null) continue;

            String contrasenia = Validador.validarContrasenia();
            if (contrasenia == null) continue;

            // Buscar admin
            boolean loginExitoso = false;
            for (Admin admin : Admin.getAdmins()) {
                if (admin.getEmail().equalsIgnoreCase(email) &&
                        admin.getContrasenia().equals(contrasenia)) {
                    admin.Login(banco);
                    loginExitoso = true;
                    break;
                }
            }

            // Buscar cliente
            if (!loginExitoso) {
                Cliente cliente = banco.buscarClientePorCredenciales(email, contrasenia);
                if (cliente != null) {
                    cliente.Login(banco);
                    loginExitoso = true;
                }
            }

            if (!loginExitoso) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            }
        }

        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
    }
}
