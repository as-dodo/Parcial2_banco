package logica;

import java.util.UUID;

public class Cuenta {

    private String numero;
    private double saldo;
    private TipoCuenta tipo;

    public Cuenta(TipoCuenta tipo) {
        this.numero = generarNumeroUnico();
        this.saldo = 0;
        this.tipo = tipo;
    }

    private String generarNumeroUnico() {
        return UUID.randomUUID().toString().substring(0, 8); // короткий код
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
        if (retirar(monto)) {
            destino.depositar(monto);
            return true;
        }
        return false;
    }
}
