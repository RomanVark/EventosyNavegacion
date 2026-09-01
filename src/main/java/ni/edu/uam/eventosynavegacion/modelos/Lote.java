package ni.edu.uam.eventosynavegacion.modelos;

import java.time.LocalDate;

public class Lote {
    private String codigo;
    private String productor;
    private double peso;
    private LocalDate fecha;


    public Lote(String codigo, String productor, double peso, LocalDate fecha) {
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
