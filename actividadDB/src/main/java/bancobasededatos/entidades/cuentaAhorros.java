package bancobasededatos.entidades;

import bancobasededatos.exepciones.SaldoInsuficienteException;

public class cuentaAhorros extends banco{
    
	
	private int numeroRetiros;
    private double porcentajeDescuentoRetiros;
    private int numeroTransferencias;
    private double porcentajeCobroTransferencias;
    private double porcentajeBonificacionDepositos;
    private int depositosRealizados;
	private int cantidadRetiros;
    
    
    public cuentaAhorros(String numeroCuenta, int saldo, String propietario, String tipo) {
		super(numeroCuenta, saldo, propietario, tipo);
		this.numeroRetiros = 0;
        this.porcentajeDescuentoRetiros = 0.01;
        this.numeroTransferencias = 0;
        this.porcentajeCobroTransferencias = 0.015;
        this.porcentajeBonificacionDepositos = 0.005;
        this.depositosRealizados = 0;
	}
    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("Error: el monto a retirar debe ser mayor que cero.");
        } else if (monto > saldo) {
            System.out.println("Error: no se puede retirar un monto mayor que el saldo disponible.");
        } else {
            saldo -= monto;
            cantidadRetiros++;
            if (cantidadRetiros > 3) {
                double comision = monto * 0.01;
                saldo -= comision;
                System.out.println("Se ha aplicado una comisión por más de 3 retiros en el mes: " + comision);
            }
            System.out.println("Se ha retirado $" + monto + " de la cuenta de ahorro.");
        }
    }
    
    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("Error: el monto a depositar debe ser mayor que cero.");
        } else {
            saldo += monto;
            if (depositosRealizados < 2) {
                double bonificacion = monto * 0.005;
                saldo += bonificacion;
                System.out.println("Se ha aplicado una bonificación por los primeros depósitos en la cuenta: " + bonificacion);
            }
            depositosRealizados++;
            System.out.println("Se ha depositado $" + monto + " en la cuenta de ahorro.");
        }
    }
    
    public void transferir(banco cuentaDestino, double monto) {
        if (monto <= 0) {
            System.out.println("Error: el monto a transferir debe ser mayor que cero.");
        } else if (monto > saldo) {
            System.out.println("Error: no se puede transferir un monto mayor que el saldo disponible.");
        } else if (cuentaDestino == null) {
            System.out.println("Error: la cuenta de destino no existe.");
        } else {
            if (cuentaDestino instanceof cuentaCorriente) {
                double comision = monto * 0.015;
                saldo -= monto;
                saldo -= comision;
                cuentaDestino.depositar(monto);
                System.out.println("Se ha transferido $" + monto + " de la cuenta de ahorro a la cuenta corriente.");
                System.out.println("Se ha aplicado una comisión del 1.5% por la transferencia: " + comision);
            } else {
                saldo -= monto;
                cuentaDestino.depositar(monto);
                System.out.println("Se ha transferido $" + monto + " de la cuenta de ahorro a la cuenta de ahorro.");
            }
        }
    }
    
    public String toString() {
        return "Cuenta de Ahorro\n" + super.toString() + "\n";
    }


   
}
