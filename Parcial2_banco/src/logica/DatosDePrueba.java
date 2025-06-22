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
        Cliente.getClientes().add(new Cliente("Cleo", "cleo@gmail.com", "1234"));
        Cliente.getClientes().add(new Cliente("Rembo", "rembo@gmail.com", "4444"));
        Cliente.getClientes().add(new Cliente("Rocky", "rocky@gmail.com", "1111"));
    }
}
