package ni.edu.uam.eventosynavegacion.dao;

import ni.edu.uam.eventosynavegacion.Interface.Crud;
import ni.edu.uam.eventosynavegacion.modelos.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDao implements Crud<Producto> {

    private final List<Producto> productos = new ArrayList<>();

    @Override
    public void agregar(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Producto Buscar(String valor) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(valor)) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public List<Producto> ObtenerRegistros() {
        return productos;
    }

    public Producto buscarporcodigo(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }
}