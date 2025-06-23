package logica;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Usuario{

    private static List<Admin> admins = new ArrayList<>();

    public Admin(String nombre, String mail, String contrasenia) {
        super(nombre, mail, contrasenia);
    }
    public Admin( String email, String contrasenia) {
        super( email, contrasenia);

    }

    public static void cargarAdmins() {
        admins.add(new Admin("Anastasia", "anastasia@gmail.com", "1234"));
        admins.add(new Admin("Django", "django@gmail.com", "abcd"));
    }

    public static List<Admin> getAdmins() {
        return admins;
    }


    @Override
    public void Login(Banco banco) {
        for (Admin admin : admins){
            if (admin.getEmail().equals(this.getEmail()) && admin.getContrasenia().equals(this.getContrasenia())) {
                admin.Menu(banco);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
    }

    @Override
    public void Menu(Banco banco) {
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
                case 0 -> verClientes(banco);
                case 1 -> verTransacciones(banco);
                case 2 -> agregarCliente(banco);
                case 3 -> eliminarCliente(banco);
                case 4 -> modificarCliente(banco);
                case 5 -> resumenSistema(banco);
                case 6, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Sesión cerrada");
                    opcion = 6;
                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } while (opcion != 6);
    }

    private void verClientes(Banco banco) {
        List<Cliente> clientes = banco.getClientes();
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
            return;
        }

        String mensaje = "Listado de clientes:\n";
        for (Cliente c : clientes) {
            mensaje += "- " + c.getNombre() + " (" + c.getEmail() + "), Cuentas: "
                    + c.getCuentas().size() + "\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }


    private void verTransacciones(Banco banco) {
        String mensaje = "Historial de transacciones:\n";
        boolean hayTransacciones = false;

        for (Cliente cliente : banco.getClientes()) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                for (Transaccion t : cuenta.getTransacciones()) {
                    mensaje += "- Cliente: " + cliente.getNombre()
                            + ", Cuenta: " + cuenta.getNumero()
                            + " (" + cuenta.getTipo() + ")"
                            + " → " + t.toString() + "\n";
                    hayTransacciones = true;
                }
            }
        }

        if (!hayTransacciones) {
            mensaje += "(No hay transacciones registradas)";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }


    private void agregarCliente(Banco banco){
        String nombre = Validador.validarNombre();
        if (nombre == null) return;

        String mail = Validador.validarEmail("Ingrese email del cliente:", true, banco);
        if (mail == null) return;

        String contrasenia = Validador.validarContrasenia();
        if (contrasenia == null) return;

        Cliente nuevo = new Cliente(nombre, mail, contrasenia);
        banco.agregarCliente(nuevo);

        JOptionPane.showMessageDialog(null,"Cliente agregado con éxito." );
    }

    private void eliminarCliente(Banco banco) {
        String mail = Validador.validarEmail("Ingrese el email del cliente:", false, banco);
        if (mail == null) return;

        Cliente clienteAEliminar = banco.buscarClientePorEmail(mail);
        if (clienteAEliminar != null) {
            banco.getClientes().remove(clienteAEliminar);
            JOptionPane.showMessageDialog(null, "Cliente eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        }
    }

    private void modificarCliente(Banco banco) {
        String mail = Validador.validarEmail("Ingrese el email del cliente:", false, banco);
        if (mail == null) return;

        Cliente cliente = banco.buscarClientePorEmail(mail);
        if (cliente != null) {
            String nuevoNombre = Validador.validarNombre();
            if (nuevoNombre == null) return;
            String nuevaContrasenia = Validador.validarContrasenia();
            if (nuevaContrasenia == null) return;

            cliente.setNombre(nuevoNombre);
            cliente.setContrasenia(nuevaContrasenia);

            JOptionPane.showMessageDialog(null, "Datos actualizados.");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        }
    }

    private void resumenSistema(Banco banco) {
        int totalClientes = banco.getClientes().size();
        int totalAdmins = Admin.getAdmins().size();

        JOptionPane.showMessageDialog(null,
                "Resumen del sistema:\n" +
                        "- Total de clientes: " + totalClientes + "\n" +
                        "- Total de administradores: " + totalAdmins);
    }
}
