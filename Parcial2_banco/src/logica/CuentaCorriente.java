package logica;

import javax.swing.*;

public class CuentaCorriente extends Cuenta {
    public CuentaCorriente() {
        super(TipoCuenta.CORRIENTE);
    }
    private static final double LIMITE_SOBREGIRO = -1000;

    @Override
    public boolean retirar(double monto) {
        if (monto <= 0) return false;

        double nuevoSaldo = saldo - monto;

        if (nuevoSaldo >= LIMITE_SOBREGIRO) {
            saldo = nuevoSaldo;
            this.agregarTransaccion(TipoTransaccion.RETIRO, monto, "Retiro con sobregiro");
            return true;
        }

        JOptionPane.showMessageDialog(null, "Excede el límite de sobregiro permitido: $" + Math.abs(LIMITE_SOBREGIRO));
        return false;
    }

}
