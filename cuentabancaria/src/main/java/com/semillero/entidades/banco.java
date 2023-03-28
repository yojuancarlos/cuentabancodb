package com.semillero.entidades;

public abstract class banco {
	protected int numeroCuenta;
	protected int saldo;
	protected String propietario;
	protected String tipo;
	
	
	public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public banco(String tipo) {
        this.tipo = tipo;
    }
    public int getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(int numeroCuenta) {
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
    public banco(int numeroCuenta, int saldo, String propietario) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.propietario = propietario;
    }
    public  void depositar (float valor) {
		saldo += valor;
	}
	public abstract void retirar(float valor)throws SaldoInsuficienteException, MaximoRetirosException;
	
	
	
}
