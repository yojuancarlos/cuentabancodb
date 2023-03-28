package main.java.com.semillero.repositorio;

public interface repositoriocrud {
    public void guardar(Object objeto);
    public void eliminar(Object objeto);
    public void actualizar(Object objeto);
    public void buscar(Object objeto);
    public List<Object> listar();
}
