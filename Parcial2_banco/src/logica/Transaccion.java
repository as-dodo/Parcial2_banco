package logica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
    private TipoTransaccion tipo;
    private double monto;
    private String detalle;
    private LocalDateTime fecha;

    public Transaccion(TipoTransaccion tipo, double monto, String detalle) {
        this.tipo = tipo;
        this.monto = monto;
        this.detalle = detalle;
        this.fecha = LocalDateTime.now(); // дата и время создания
    }

    public Transaccion(TipoTransaccion tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now(); // дата и время создания
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getDetalle() {
        return detalle;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return tipo + " $" + monto + " (" + detalle + ") - " + fecha.format(formato);
    }
}
