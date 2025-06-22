package logica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{
    static List<Cliente> clientes = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();
    private int nro;
    private static int indice=0;
    public Cliente(String nombre, String mail, String contrasenia) {
        super(nombre, mail, contrasenia);
        indice++;
        this.nro = indice;
    }
    public Cliente(String mail, String contrasenia) {
        super( mail, contrasenia);
        indice++;
        this.nro = indice;
    }

    public int getNro() {
        return nro;
    }
    public void setNro(int nro) {
        this.nro = nro;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    @Override
    public void Login() {
        Cliente encontrado = buscarClientePorCredenciales(this.getMail(), this.getContrasenia());
        if (encontrado != null) {
            encontrado.Menu();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
    }

    private Cliente buscarClientePorCredenciales(String mail, String contrasenia) {
        for (Cliente cliente : clientes) {
            if (cliente.getMail().equalsIgnoreCase(mail) &&
                    cliente.getContrasenia().equals(contrasenia)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public void Menu() {
        String[] opciones = {
                "Ver movimientos",
                "Retirar",
                "Transferir",
                "Salir"
        };

        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Elija una opción:",
                    "Menú del cliente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0 -> verMovimientos();
                case 1 -> retirar();
                case 2 -> transferir();
                case 3, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Sesión cerrada");
                    opcion = 3;
                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (opcion != 3);
    }

    private void verMovimientos() {
        JOptionPane.showMessageDialog(null, "Aquí se mostrarían los movimientos.");
    }

    private void retirar() {
        JOptionPane.showMessageDialog(null, "Aquí iría la lógica de retiro.");
    }

    private void transferir() {
        JOptionPane.showMessageDialog(null, "Aquí iría la lógica de transferencia.");
    }

}