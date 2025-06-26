package logica;

public abstract class Usuario {
    private String nombre;
    private String email;
    private String contrasenia;

    public Usuario(String nombre, String email, String contrasenia) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
    }
    public Usuario(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public abstract void Login(Banco banco);

    public abstract void Menu(Banco banco);
}
