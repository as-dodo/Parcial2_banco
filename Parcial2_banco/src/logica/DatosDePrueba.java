package logica;

public class DatosDePrueba {

    public static void cargar(Banco banco) {
        cargarClientes(banco);
        cargarAdmins();
    }

    private static void cargarClientes(Banco banco) {
        Cliente cleo = new Cliente("Cleo", "cleo@gmail.com", "1234");
        cleo.abrirCuenta(TipoCuenta.CORRIENTE).depositar(1000);

        Cliente rembo = new Cliente("Rembo", "rembo@gmail.com", "4444");
        rembo.abrirCuenta(TipoCuenta.AHORRO).depositar(500);

        Cliente rocky = new Cliente("Rocky", "rocky@gmail.com", "1111");
        rocky.abrirCuenta(TipoCuenta.CORRIENTE).depositar(300);
        rocky.abrirCuenta(TipoCuenta.AHORRO).depositar(700);

        banco.agregarCliente(cleo);
        banco.agregarCliente(rembo);
        banco.agregarCliente(rocky);

    }

    private static void cargarAdmins() {
        Admin.getAdmins().add(new Admin("Anastasia", "anastasia@gmail.com", "1234"));
        Admin.getAdmins().add(new Admin("Django", "django@gmail.com", "abcd"));
    }
}
