package main.java.com.semillero.repositorio;

import java.sql.DriverManager;

public class cuentaDb implements repositoriocrud{
    
    private String cadenaConexion;
    public cuentaDb(){
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            cadenaConexion="jdbc:sqlite:pruebas.db";
            creartabla();
        } catch (Exception e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }



    }
    public  void creartabla() {
        try (Connection conexion = DriverManager.getConnection(cadenaconexion)) {
            String sql="CREATE TABLE IF NOT EXISTS cuentas (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " propietario TEXT NOT NULL,\n"
            + " saldo integer NOT NULL,\n"
            + " tipo text NOT NULL ,\n"
            + " sobrecargo integer NULL,\n"
            + " abono integer  NULL,\n"
            + " numerocuenta TEXT NOT NULL UNIQUE,\n"
            + " numeroretiros integer  NULL,\n"
            + ");";
        } catch (Exception e) {
            System.err.println("Error al crear la tabla: " + e);
        }
        
    }
    
    
    
    
    
    
    
    @Override
public void guardar(Object objeto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'guardar'");
}

@Override
public void eliminar(Object objeto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
}

@Override
public void actualizar(Object objeto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
}

@Override
public void buscar(Object objeto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscar'");
}

@Override
public List<Object> listar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listar'");
}
}
