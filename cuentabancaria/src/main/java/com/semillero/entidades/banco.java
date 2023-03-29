package com.semillero.entidades;

public  class banco {
	protected String numeroCuenta;
	protected  int saldo;
	protected  String propietario;
	protected String tipo;

	public banco(String numeroCuenta, int saldo, String propietario, String tipo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.propietario = propietario;
        this.tipo = tipo;
    }
    
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
    
    public  void depositar (float valor) {
		saldo += valor;
	}
	
	
    public void transferir(float valor,int numeroCuenta){

    }
	
	
}
