package logica;

public class CuentaAhorro extends Cuenta {
    public CuentaAhorro() {
        super(TipoCuenta.AHORRO);
    }

    @Override
    public boolean retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            this.agregarTransaccion(TipoTransaccion.RETIRO, monto, "Retiro");
            return true;
        }
        return false;
    }
}
