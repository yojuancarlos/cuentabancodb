package main.java.com.semillero.repositorio;

import java.sql.DriverManager;

import com.semillero.entidades.banco;

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
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);
        } catch (Exception e) {
            System.err.println("Error al crear la tabla: " + e);
        }
        
    }
    
    
@Override
public void guardar(Object objeto) {

    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        banco banco = (banco) objeto;
        String sentenciaSql = "INSERT INTO banco (propietario, saldo, tipo, numerocuenta) " +
                " VALUES('" + banco.getPropietario() + "', '" 
                + "', " + banco.getSaldo() + ", '" + banco.getNumeroCuenta()
                 + "');";
        Statement sentencia = conexion.createStatement();
        sentencia.execute(sentenciaSql);
    } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
    } catch (Exception e) {
        System.err.println("Error " + e.getMessage());
    }
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
public List<?> listar() {
    List<banco> bancos  = new ArrayList<banco>();

    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        String sentenciaSql = "SELECT * FROM personas";
        PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
        ResultSet resultadoConsulta = sentencia.executeQuery();

        if (resultadoConsulta != null) {
            while (resultadoConsulta.next()) {
                banco banco = null;
                String propietario = resultadoConsulta.getString("propietario");
                int saldo = resultadoConsulta.getInt("saldo");
                String tipo = resultadoConsulta.getString("tipo");
                String numerocuenta = resultadoConsulta.getString("numerocuenta");

                banco = new banco(propietario,saldo,tipo, numerocuenta );
                bancos.add(banco);
            }
            return bancos;
        }
    } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
    }
    return null;  
}
}
