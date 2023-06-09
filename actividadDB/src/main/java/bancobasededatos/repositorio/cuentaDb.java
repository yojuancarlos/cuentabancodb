package bancobasededatos.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import bancobasededatos.entidades.banco;
import bancobasededatos.entidades.cuentaAhorros;
import bancobasededatos.entidades.cuentaCorriente;

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
        try (Connection conexion = DriverManager.getConnection(this.cadenaConexion)) {
            String sql="CREATE TABLE IF NOT EXISTS cuentas (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " numerocuenta TEXT NOT NULL UNIQUE,\n"
            + " saldo integer NOT NULL,\n"
            + " propietario TEXT NOT NULL,\n"
            + " tipo text NOT NULL ,\n"
            + " sobrecargo integer NULL,\n"
            + " abono integer  NULL,\n"
            + " numeroretiros integer  NULL\n"
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
        String sentenciaSql = "INSERT INTO cuentas (numerocuenta,saldo,propietario,tipo) " +
                " VALUES('" + banco.getNumeroCuenta()  + "', "  + banco.getSaldo()
                 + ", '" + banco.getPropietario()+ "', '" + banco.getTipo() + "');";

                
                 
        Statement sentencia = conexion.createStatement();
        sentencia.execute(sentenciaSql);
    } catch (SQLException e) {
        System.err.println("Error de conexión222: " + e);
    } catch (Exception e) {
        System.err.println("Error " + e.getMessage());
    }
}

@Override
public Object eliminar(String numCuenta) {
    // TODO Auto-generated method stub
    
    try (Connection conexion = DriverManager.getConnection(this.cadenaConexion)) {
        String sentenciaSql = "DELETE FROM cuentas WHERE numeroCuenta = '" + numCuenta + "';";
        Statement sentencia = conexion.createStatement();
        sentencia.execute(sentenciaSql);
    } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
    } catch (Exception e) {
        System.err.println("Error " + e.getMessage());
    }
    return numCuenta;


}

@Override
public void actualizar(String numeroCuenta, Object cuentaActualizada) {
    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        banco cuenta = (banco) cuentaActualizada;
        String sentenciaSql = "UPDATE cuentas SET "
                     
                     + "numeroCuenta = ?, "
                     + "saldo = ?, "
                     + "propietario = ?, "
                     + "tipo = ? "
                     + "WHERE numeroCuenta = ?;";
                     
        PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
        sentencia.setString(1, cuenta.getNumeroCuenta());
        sentencia.setInt(2, cuenta.getSaldo());
        sentencia.setString(3, cuenta.getPropietario());
        sentencia.setString(4, cuenta.getTipo().toString());
        
        if (cuenta instanceof cuentaAhorros) {
            sentencia.setInt(6, ((cuentaAhorros) cuenta).getDepositosRealizados());
            sentencia.setNull(7, Types.INTEGER);
        } else if (cuenta instanceof cuentaCorriente) {
            sentencia.setNull(6, Types.INTEGER);
            sentencia.setInt(7, ((cuentaCorriente) cuenta).getNumTransferenciasAhorro());
        }
       sentencia.executeUpdate();
       conexion.close();
    } catch (SQLException e) {
        System.out.println("Error al actualizar la cuenta"  + e.getMessage());
    }
}

@Override
public Object buscar(String numCuenta) {
    
    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        String sentenciaSQL = "SELECT * FROM cuentas WHERE numerocuenta = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
        sentencia.setString(1,numCuenta);
        ResultSet resultadoConsulta = sentencia.executeQuery();
        if (resultadoConsulta != null && resultadoConsulta.next()) {
            banco banco = null;
            String numerocuenta = resultadoConsulta.getString("numerocuenta");
                int saldo = resultadoConsulta.getInt("saldo");
                String propietario = resultadoConsulta.getString("propietario");
                String tipo = resultadoConsulta.getString("tipo");

            banco = new banco(numerocuenta,saldo,propietario,tipo);
            return banco;
        }

    } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
    }
    return null;

}

@Override
public List<?> listar() {
    List<banco> bancos  = new ArrayList<banco>();

    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        String sentenciaSql = "SELECT * FROM cuentas";
        PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
        ResultSet resultadoConsulta = sentencia.executeQuery();

        if (resultadoConsulta != null) {
            while (resultadoConsulta.next()) {
                banco banco = null;
                String numerocuenta = resultadoConsulta.getString("numerocuenta");
                int saldo = resultadoConsulta.getInt("saldo");
                String propietario = resultadoConsulta.getString("propietario");
                String tipo = resultadoConsulta.getString("tipo");
                

                banco = new banco(numerocuenta,saldo,propietario,tipo);
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
