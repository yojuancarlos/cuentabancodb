package main.java.com.semillero.repositorio;

import java.util.List;

public interface repositoriocrud {
    public void guardar(Object objeto);
    public Object eliminar(String numCuenta);
    public void actualizar(Object objeto);
    public Object buscar(String numCuenta);
    public List<?> listar();
}
