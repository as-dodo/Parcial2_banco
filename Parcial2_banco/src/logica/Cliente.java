package logica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{
    private List<Cuenta> cuentas = new ArrayList<>();

    private int nro;
    private static int indice=0;

    public Cliente(String nombre, String email, String contrasenia) {
        super(nombre, email, contrasenia);
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
    public void Login(Banco banco) {
        Cliente encontrado = banco.buscarClientePorCredenciales(this.getEmail(), this.getContrasenia());
        if (encontrado != null) {
            encontrado.Menu(banco);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
    }



    @Override
    public void Menu(Banco banco) {
        String[] opciones = {
                "Ver movimientos",
                "Ver saldo",
                "Depositar",
                "Retirar",
                "Transferir",
                "Abrir nueva cuenta",
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
                case 4 -> transferir(banco);
                case 5 -> abrirNuevaCuenta();
                case 6, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Sesión cerrada");
                    opcion = 6;
                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (opcion != 6);
    }

    public Cuenta abrirCuenta(TipoCuenta tipo) {
        Cuenta nueva = switch (tipo) {
            case AHORRO -> new CuentaAhorro();
            case CORRIENTE -> new CuentaCorriente();
        };
        cuentas.add(nueva);
        return nueva;
    }

    private void abrirNuevaCuenta() {
        String[] opciones = { "Cuenta Ahorro", "Cuenta Corriente" };
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Seleccione el tipo de cuenta:",
                "Abrir nueva cuenta",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == JOptionPane.CLOSED_OPTION || seleccion == -1) return;

        TipoCuenta tipo = (seleccion == 0) ? TipoCuenta.AHORRO : TipoCuenta.CORRIENTE;

        Cuenta nueva = abrirCuenta(tipo);
        JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente: " + nueva.getNumero() + " (" + tipo + ")");
    }

    private Cuenta seleccionarCuenta() {
        if (cuentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tienes cuentas asociadas.");
            return null;
        }

        if (cuentas.size() == 1) {
            return cuentas.get(0);
        }

        String[] opciones = cuentas.stream()
                .map(c -> c.getNumero() + " (" + c.getTipo() + ")")
                .toArray(String[]::new);

        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona una cuenta:",
                "Cuentas disponibles",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == JOptionPane.CLOSED_OPTION || seleccion == -1) return null;

        return cuentas.get(seleccion);
    }


    private void verMovimientos() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta == null) return;

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

        Cuenta cuenta = seleccionarCuenta();
        if (cuenta == null) return;

        cuenta.depositar(monto);
        cuenta.agregarTransaccion(TipoTransaccion.DEPOSITO, monto, "Depósito realizado");

        JOptionPane.showMessageDialog(null, "Depósito exitoso. Nuevo saldo: $" + cuenta.getSaldo());
    }

    private void retirar() {
        Double monto = Validador.validarMonto("Ingrese el monto a retirar:");
        if (monto == null) return;

        Cuenta cuenta = seleccionarCuenta();
        if (cuenta == null) return;

        if (cuenta.retirar(monto)) {
            JOptionPane.showMessageDialog(null, "Retiro exitoso. Nuevo saldo: $" + cuenta.getSaldo());
        } else {
            JOptionPane.showMessageDialog(null, "Fondos insuficientes.");
        }
    }

    private void transferir(Banco banco) {
        String emailDestino = Validador.validarEmail("Ingrese el email del destinatario:", false, banco);
        if (emailDestino == null) return;

        if (emailDestino.equalsIgnoreCase(this.getEmail())) {
            JOptionPane.showMessageDialog(null, "No puedes transferirte a ti mismo.");
            return;
        }

        Cliente destinatario = banco.buscarClientePorEmail(emailDestino);
        if (destinatario == null) {
            JOptionPane.showMessageDialog(null, "Cliente destinatario no encontrado.");
            return;
        }

        Double monto = Validador.validarMonto("Ingrese el monto a transferir:");
        if (monto == null) return;

        Cuenta miCuenta = seleccionarCuenta();
        if (miCuenta == null) return;

        Cuenta cuentaDestino = destinatario.getCuentas().get(0);
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