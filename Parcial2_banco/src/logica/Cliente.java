package logica;

import javax.swing.*;
import java.util.LinkedList;

public class Cliente extends Usuario{
    static LinkedList<Cliente> clientes = new LinkedList<Cliente>();
    private int nro;
    private static int indice=0;
    public Cliente(String nombre, String mail, String contrasenia) {
        super(nombre, mail, contrasenia);
        indice++;
        this.nro = indice;
    }
    public Cliente(String mail, String contrasenia) {
        super( mail, contrasenia);
        indice++;
        this.nro = indice;
    }
    public int getNro() {
        return nro;
    }
    public void setNro(int nro) {
        this.nro = nro;
    }

    @Override
    public void Login() {
        for (Cliente cliente : clientes) {
            if (cliente.getMail().equals(this.getMail()) && cliente.getContrasenia().equals(this.getContrasenia())) {
                cliente.Menu();


            }
        }
    }
    @Override
    public void Menu() {
        String[] opcionesCliente = {
                "Ver movimientos","Retirar","Transferir","Salir"
        };
        int algo = JOptionPane.showOptionDialog(null, "Elija opcion cliente", "Menu cliente", 0, 0, null, opcionesCliente, opcionesCliente[0]);
    }
    public static void carga() {


        clientes.add(new Cliente("Juan","juan@gmail.com","1234"));
        clientes.add(new Cliente("Murad","murad@gmail.com","4444"));
        clientes.add(new Cliente("Luciano","Luciano@gmail.com","1111"));
    }
}