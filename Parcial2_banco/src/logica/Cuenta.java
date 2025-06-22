package logica;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private static int contador = 1;
    private List<Transaccion> transacciones = new ArrayList<>();

    private String numero;
    private double saldo;
    private TipoCuenta tipo;

    public Cuenta(TipoCuenta tipo) {
        this.numero = generarNumeroUnico();
        this.saldo = 0;
        this.tipo = tipo;
    }

    private String generarNumeroUnico() {
        return "C" + (contador++);
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void agregarTransaccion(TipoTransaccion tipo, double monto, String detalle) {
        transacciones.add(new Transaccion(tipo, monto, detalle));
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public boolean transferirA(Cuenta destino, double monto) {
        if (this.retirar(monto)) {
            destino.depositar(monto);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cuenta " + numero + " (" + tipo + ") - Saldo: $" + saldo;
    }
}
