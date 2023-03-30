package interfaces;

import java.util.List;

public interface Operations<T> {
    List<T> listar();
    T buscar(int t);
    void insertar(T t);
    void actualizar(T t);
    void eliminar(int t);
}
