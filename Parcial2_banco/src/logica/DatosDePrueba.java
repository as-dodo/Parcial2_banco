package logica;

public class DatosDePrueba {
    public static void cargar() {
        cargarAdmins();
        cargarClientes();
    }

    private static void cargarAdmins() {
        Admin.getAdmins().add(new Admin("Anastasia", "anastasia@gmail.com", "1234"));
        Admin.getAdmins().add(new Admin("Django", "django@gmail.com", "abcd"));
    }

    private static void cargarClientes() {
        Cliente cliente1 = new Cliente("Cleo", "cleo@gmail.com", "1234");
        Cliente cliente2 = new Cliente("Rembo", "rembo@gmail.com", "4444");
        Cliente cliente3 = new Cliente("Rocky", "rocky@gmail.com", "1111");

        cliente1.getCuentas().get(0).depositar(1000);
        cliente2.getCuentas().get(0).depositar(800);
        cliente3.getCuentas().get(0).depositar(1200);

        Cliente.getClientes().add(cliente1);
        Cliente.getClientes().add(cliente2);
        Cliente.getClientes().add(cliente3);
    }
}
