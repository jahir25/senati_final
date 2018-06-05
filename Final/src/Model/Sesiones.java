package Model;

public class Sesiones {
    String nombre, apellido, id, perfil, valdsession, flagsesion;

    public Sesiones(String valdsession, String perfil, String flagsesion, String nombre, String apellido, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.perfil = perfil;
        this.valdsession = valdsession;
        this.flagsesion = flagsesion;
    }

    public void setFlagsesion(String flagsesion) {
        this.flagsesion = flagsesion;
    }

    public String getFlagsesion() {
        return flagsesion;
    }

    public void setValdsession(String valdsession) {
        this.valdsession = valdsession;
    }

    public String getValdsession() {
        return valdsession;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getId() {
        return id;
    }

    public String getPerfil() {
        return perfil;
    }
    
}
