package bancobasededatos.entidades;

import java.sql.Connection;
import java.sql.DriverManager;

public   class banco {
	protected String numeroCuenta;
	protected  int saldo;
	protected  String propietario;
	protected String tipo;

	
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }


    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }


    public int getSaldo() {
        return saldo;
    }


    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }


    public String getPropietario() {
        return propietario;
    }


    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public banco(String numeroCuenta, int saldo, String propietario, String tipo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.propietario = propietario;
        this.tipo = tipo;
    }

    

    public void depositar(double monto) {
        double montoConAdicional = monto;
        // Solo se aplica el adicional para los primeros dos depósitos.
        if (this.saldo == 0 && monto > 0) {
            montoConAdicional += (monto * 0.005);
        } else if (this.saldo > 0 && monto > 0) {
            montoConAdicional += (monto * 0.005);
            this.saldo += montoConAdicional;
        }
        this.saldo += monto;
    }

    public void retirar(double monto) {
        if (monto > 0 && monto <= this.saldo) {
            this.saldo -= monto;
        }
    }
	
	
    public void transferir(double monto, banco cuentaDestino) {
        if (this instanceof cuentaAhorros && cuentaDestino instanceof cuentaCorriente) {
            monto += (monto * 0.015);
        } else if (this instanceof cuentaCorriente && cuentaDestino instanceof cuentaAhorros) {
            monto += (monto * 0.015);
        } else if (this instanceof cuentaAhorros && cuentaDestino instanceof cuentaCorriente) {
            monto += (monto * 0.02);
        }

        if (monto <= this.saldo) {
            this.saldo -= monto;
            cuentaDestino.depositar(monto);
        }
    }
	
	
}
