package ni.edu.uam.eventosynavegacion.modelos;

public class Artesania {

    private String nombre;
    private String categoria;
    private double precio;
    private String rutaImagen;

    public Artesania( String nombre, String categoria, double precio, String rutaImagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }

}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getCategoria() {
    return categoria;
}

public void setCategoria(String categoria) {
    this.categoria = categoria;
}

public double getPrecio() {
    return precio;
}

public void setPrecio(double precio) {
    this.precio = precio;
}



public String getRutaImagen() {
    return rutaImagen;
}

public void setRutaImagen(String rutaImagen) {
    this.rutaImagen = rutaImagen;
}
