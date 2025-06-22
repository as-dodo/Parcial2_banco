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
        cuentas.add(new Cuenta(TipoCuenta.AHORRO));
    }
    public Cliente(String mail, String contrasenia) {
        super( mail, contrasenia);
        indice++;
        this.nro = indice;
        cuentas.add(new Cuenta(TipoCuenta.AHORRO));
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

    public Cuenta getCuenta() {
        return cuentas.get(0);
    }


    @Override
    public void Login() {
        Cliente encontrado = buscarClientePorCredenciales(this.getEmail(), this.getContrasenia());
        if (encontrado != null) {
            encontrado.Menu();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
    }

    private Cliente buscarClientePorCredenciales(String mail, String contrasenia) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equalsIgnoreCase(mail) &&
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
                "Ver saldo",
                "Depositar",
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
                case 1 -> verSaldo();
                case 2 -> depositar();
                case 3 -> retirar();
                case 4 -> transferir();
                case 5, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Sesión cerrada");
                    opcion = 5;
                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (opcion != 5);
    }

    private void verMovimientos() {
        Cuenta cuenta = cuentas.get(0);
        List<Transaccion> transacciones = cuenta.getTransacciones();

        if (transacciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay transacciones registradas.");
            return;
        }

        String mensaje = "Movimientos de la cuenta Nº " + cuenta.getNumero() + ":\n\n";
        for (Transaccion t : transacciones) {
            mensaje += "- " + t.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void depositar() {
        Double monto = Validador.validarMonto("Ingrese el monto a depositar:");
        if (monto == null) return;

        Cuenta cuenta = cuentas.get(0);
        cuenta.depositar(monto);
        cuenta.agregarTransaccion(TipoTransaccion.DEPOSITO, monto, "Depósito realizado");

        JOptionPane.showMessageDialog(null, "Depósito exitoso. Nuevo saldo: $" + cuenta.getSaldo());
    }

    private void retirar() {
        Double monto = Validador.validarMonto("Ingrese el monto a retirar:");
        if (monto == null) return;

        Cuenta cuenta = getCuenta();
        if (cuenta.retirar(monto)) {
            JOptionPane.showMessageDialog(null, "Retiro exitoso. Nuevo saldo: $" + cuenta.getSaldo());
        } else {
            JOptionPane.showMessageDialog(null, "Fondos insuficientes.");
        }
    }

    private void transferir() {
        String emailDestino = Validador.validarEmail("Ingrese el email del destinatario:", false);
        if (emailDestino == null) return;

        if (emailDestino.equalsIgnoreCase(this.getEmail())) {
            JOptionPane.showMessageDialog(null, "No puedes transferirte a ti mismo.");
            return;
        }

        Cliente destinatario = null;
        for (Cliente c : Cliente.getClientes()) {
            if (c.getEmail().equalsIgnoreCase(emailDestino)) {
                destinatario = c;
                break;
            }
        }

        if (destinatario == null) {
            JOptionPane.showMessageDialog(null, "Cliente destinatario no encontrado.");
            return;
        }

        Double monto = Validador.validarMonto("Ingrese el monto a transferir:");
        if (monto == null) return;

        Cuenta miCuenta = this.getCuentas().get(0);  // primera cuenta del cliente actual
        Cuenta cuentaDestino = destinatario.getCuentas().get(0);  // primera cuenta del destinatario

        if (miCuenta.transferirA(cuentaDestino, monto)) {
            JOptionPane.showMessageDialog(null, "Transferencia exitosa a " + destinatario.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "Fondos insuficientes para transferir.");
        }
    }

    private void verSaldo() {
        if (cuentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tienes cuentas asociadas.");
            return;
        }

        String mensaje = "Saldo de tus cuentas:\n";
        for (Cuenta c : cuentas) {
            mensaje += "Cuenta Nº " + c.getNumero() + ": $" + c.getSaldo() + "\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }


}