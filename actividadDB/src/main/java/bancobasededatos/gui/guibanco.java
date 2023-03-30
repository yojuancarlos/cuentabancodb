package bancobasededatos.gui;

import java.util.List;
import java.util.Scanner;

import bancobasededatos.entidades.banco;
import bancobasededatos.servicios.serviciosbanco;

public class guibanco {
    private boolean running = true;
    private serviciosbanco serviciosbanco;

    public guibanco() {
        serviciosbanco = new serviciosbanco();
    }

    public void iniciar() {
        System.out.println("Bienvenido al sistema de bancos");

        while (running) {
            System.out.println("1. Crear cuenta");
            System.out.println("2. Listar cuenta");
            System.out.println("3. Buscar cuenta");
            System.out.println("4. Eliminar cuenta");
            System.out.println("5. Realizar depósito");
            System.out.println("6. Realizar retiro");
            System.out.println("7. Realizar transferencia");
            System.out.println("8. Salir");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            seleccion(opcion);
        }

    }

    private void seleccion(int seleccion) {
        switch (seleccion) {
            case 1:
                crearPersona();
                break;
            case 2:
                listarPersonas();
                break;
            case 3:
                buscarPersona();
                break;
            case 4:
                eliminarPersona();
                break;
            case 5:
            eliminarPersona();
            break;
            case 6:
            eliminarPersona();
            break;
            case 7:
            eliminarPersona();
            break;
            case 8:
                salir();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private void crearPersona() {
        System.out.println("Crear banco");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numerocuenta: ");
        String numerocuenta = scanner.nextLine();   
        System.out.println("saldo: ");
        int saldo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("propietario: ");
        String propietario = scanner.nextLine();
        System.out.println("tipo de cuenta: ");
        String tipo = scanner.nextLine();

        banco persona = new banco(numerocuenta,saldo,propietario,tipo);
        serviciosbanco.guardarcuenta(persona);

    }

    private void listarPersonas() {
        System.out.println("Listando cuentas");
        List<banco> cuentasenbasededatos = serviciosbanco.listarcuentas();

        for (banco cuentaenbasededatos : cuentasenbasededatos) {
            System.out.println(cuentaenbasededatos.getNumeroCuenta());
        }
    }

    private void buscarPersona() {
        System.out.println("Buscar cuenta");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        try {
            banco cuentaencontrada = serviciosbanco.buscarcuenta(numerodecuenta);
            System.out.println("cuenta encontrada: " + cuentaencontrada.getNumeroCuenta());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarPersona() {
        System.out.println("Eliminarcuenta");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        try {
            serviciosbanco.eliminarPersona(numerodecuenta);
            System.out.println("cuenta eliminada");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void salir() {
        System.out.println("Salir");
        running = false;
    }
}
