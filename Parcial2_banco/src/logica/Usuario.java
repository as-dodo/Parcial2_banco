package logica;

public abstract class Usuario {
    private String nombre;
    private String mail;
    private String contrasenia;

    public Usuario(String nombre, String mail, String contrasenia) {
        this.nombre = nombre;
        this.mail = mail;
        this.contrasenia = contrasenia;
    }
    public Usuario(String mail, String contrasenia) {
        this.mail = mail;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean cambiarContrasenia(String actual, String nueva) {
        if (!this.contrasenia.equals(actual)) {
            return false;
        }
        setContrasenia(nueva);
        return true;
    }

    public abstract void Login();

    public abstract void Menu();
}
