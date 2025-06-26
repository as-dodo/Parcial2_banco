package logica;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {

    private static int contador = 1;
    protected List<Transaccion> transacciones = new ArrayList<>();

    private String numero;
    protected double saldo;
    protected TipoCuenta tipo;

    public Cuenta(TipoCuenta tipo) {
        this.tipo = tipo;
        this.numero = generarNumeroUnico();
        this.saldo = 0;
    }

    public String getNumero() {
        return numero;
    }

    private String generarNumeroUnico() {
        String prefijo = switch (tipo) {
            case AHORRO -> "CA";
            case CORRIENTE -> "CC";
        };
        return prefijo + (contador++);
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
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

    public abstract boolean retirar(double monto);

    public boolean transferirA(Cuenta destino, double monto) {
        if (this.retirar(monto)) {
            destino.depositar(monto);

            this.agregarTransaccion(TipoTransaccion.TRANSFERENCIA, monto,
                    "Transferencia a cuenta " + destino.getNumero());

            destino.agregarTransaccion(TipoTransaccion.TRANSFERENCIA, monto,
                    "Transferencia recibida de cuenta " + this.getNumero());

            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Cuenta " + numero + " (" + tipo + ") - Saldo: $" + saldo;
    }
}
