import logica.Admin;
import logica.Cliente;
import logica.rol;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        Admin.cargarAdmins();
        Cliente.carga();

        Admin admin = new Admin("anastasia@gmail.com", "1234");
        admin.Login();
//        int opcion;
//        String mail,contrasenia;
//        do {
//
//            opcion = JOptionPane.showOptionDialog(null, "", "", 0, 0, null, rol.values(), rol.values());
//
//            switch (opcion) {
//                case 1:
//                    mail = JOptionPane.showInputDialog("Ingrese mail");
//                    contrasenia = JOptionPane.showInputDialog("Ingrese contrasenia");
//                    Cliente nuevo = new Cliente(mail,contrasenia);
//                    nuevo.Login();
//                    break;
//                case 0:
//                    mail = JOptionPane.showInputDialog("Ingrese mail");
//                    contrasenia = JOptionPane.showInputDialog("Ingrese contrasenia");
//                    Admin nuevo2= new Admin(mail,contrasenia);
//                    nuevo2.Login();
//                    break;
//
//                default:
//                    break;
//            }
//
//        } while (opcion!=2);
//

    }
}
