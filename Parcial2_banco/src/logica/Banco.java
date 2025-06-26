package logica;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Cliente> clientes = new ArrayList<>();

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarClientePorEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)) return c;
        }
        return null;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente buscarClientePorCredenciales(String email, String contrasenia) {
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email) && c.getContrasenia().equals(contrasenia)) {
                return c;
            }
        }
        return null;
    }
}
