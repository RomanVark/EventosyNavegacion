package ni.edu.uam.eventosynavegacion.Interface;

import java.util.List;

public interface Crud <T>{
    void agregar(T objeto);

    T Buscar(String valor);

    List<T> ObtenerRegistros();

}
