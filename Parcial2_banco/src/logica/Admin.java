package logica;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Usuario{

    private static List<Admin> admins = new ArrayList<>();

    public Admin(String nombre, String mail, String contrasenia) {
        super(nombre, mail, contrasenia);
    }
    public Admin( String mail, String contrasenia) {
        super( mail, contrasenia);

    }

    public static void cargarAdmins() {
        admins.add(new Admin("Anastasia", "anastasia@gmail.com", "1234"));
        admins.add(new Admin("Django", "django@gmail.com", "abcd"));
    }

    public static List<Admin> getAdmins() {
        return admins;
    }


    @Override
    public void Login() {
        for (Admin admin : admins){
            if (admin.getEmail().equals(this.getEmail()) && admin.getContrasenia().equals(this.getContrasenia())) {
                admin.Menu();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
    }

    @Override
    public void Menu() {
        String[] opciones = {
                "Ver clientes",
                "Ver transacciones",
                "Agregar cliente",
                "Eliminar cliente",
                "Modificar cliente",
                "Resumen del sistema",
                "Salir"
        };

        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Elija una opción:",
                    "Menú del administrador",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0 -> verClientes();
                case 1 -> verTransacciones();
                case 2 -> agregarCliente();
                case 3 -> eliminarCliente();
                case 4 -> modificarCliente();
                case 5 -> resumenSistema();
                case 6, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Sesión cerrada");
                    opcion = 6;
                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } while (opcion != 6);
    }

    private void verClientes() {
        JOptionPane.showMessageDialog(null, "Listado de clientes:\n(aquí se mostrarían los datos)");
    }

    private void verTransacciones() {
        JOptionPane.showMessageDialog(null, "Historial de transacciones:\n(aquí iría la lista de operaciones)");
    }

    private void agregarCliente(){
        String nombre = Validador.validarNombre();
        if (nombre == null) return;

        String mail = Validador.validarEmail("Ingrese email del cliente:", true);
        if (mail == null) return;

        String contrasenia = Validador.validarContrasenia();
        if (contrasenia == null) return;

        Cliente nuevo = new Cliente(nombre, mail, contrasenia);
        Cliente.clientes.add(nuevo);

        JOptionPane.showMessageDialog(null,"Cliente agregado con éxito." );
    }

    private void eliminarCliente() {
        String mail = Validador.validarEmail("Ingrese el email del cliente:", false);
        if (mail == null) return;

        Cliente clienteAEliminar = null;
        for (Cliente c : Cliente.clientes) {
            if (c.getEmail().equalsIgnoreCase(mail)) {
                clienteAEliminar = c;
                break;
            }
        }

        if (clienteAEliminar != null) {
            Cliente.clientes.remove(clienteAEliminar);
            JOptionPane.showMessageDialog(null, "Cliente eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        }
    }

    private void modificarCliente() {
        String mail = Validador.validarEmail("Ingrese el email del cliente:", false);
        if (mail == null) return;

        for (Cliente c : Cliente.clientes) {
            if (c.getEmail().equalsIgnoreCase(mail)) {
                String nuevoNombre = Validador.validarNombre();
                if (nuevoNombre == null) return;
                String nuevaContrasenia = Validador.validarContrasenia();
                if (nuevaContrasenia == null) return;

                c.setNombre(nuevoNombre);
                c.setContrasenia(nuevaContrasenia);

                JOptionPane.showMessageDialog(null, "Datos actualizados.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
    }

    private void resumenSistema() {
        int totalClientes = Cliente.clientes.size();
        int totalAdmins = Admin.getAdmins().size();
        // int totalCuentas = Cuenta.getCantidadTotal(); // если потом добавим
        // int totalTransacciones = Transaccion.getCantidad(); // если будет

        JOptionPane.showMessageDialog(null,
                "Resumen del sistema:\n" +
                        "- Total de clientes: " + totalClientes + "\n" +
                        "- Total de administradores: " + totalAdmins
        );
    }





}
