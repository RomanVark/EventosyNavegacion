package ni.edu.uam.eventosynavegacion.modelos;

public class Lote {
    private String codigo;
    private String productor;
    private double peso;
    private String fecha;


    public Lote(String codigo, String productor, double peso, String fecha) {
        this.codigo = codigo;
        this.productor = productor;
        this.peso = peso;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
