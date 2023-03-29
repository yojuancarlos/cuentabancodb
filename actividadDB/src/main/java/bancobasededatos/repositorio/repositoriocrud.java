package bancobasededatos.repositorio;

import java.util.List;

public interface repositoriocrud {
    public void guardar(Object objeto);
    public Object eliminar(String identificador);
    public void actualizar(Object objeto);
    public Object buscar(String identificador);
    public List<?> listar();
}
