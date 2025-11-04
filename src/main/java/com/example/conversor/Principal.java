package com.example.conversor;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private final ConsultaTasa consultaTasa;

    public Principal() {
        this.consultaTasa = new ConsultaTasa();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción (1-13): ");
            String opcionLine = scanner.nextLine();
            int opcion;
            try {
                opcion = Integer.parseInt(opcionLine.trim());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Por favor ingrese un número entre 1 y 13.");
                continue;
            }

            if (opcion == 13) {
                salir = true;
                System.out.println("Saliendo. ¡Hasta luego!");
                continue;
            }

            String from;
            String to;

            switch (opcion) {
                case 1 -> { from = Moneda.USD.code(); to = Moneda.ARS.code(); }
                case 2 -> { from = Moneda.ARS.code(); to = Moneda.USD.code(); }
                case 3 -> { from = Moneda.USD.code(); to = Moneda.BRL.code(); }
                case 4 -> { from = Moneda.BRL.code(); to = Moneda.USD.code(); }
                case 5 -> { from = Moneda.USD.code(); to = Moneda.COP.code(); }
                case 6 -> { from = Moneda.COP.code(); to = Moneda.USD.code(); }
                case 7 -> { from = Moneda.CLP.code(); to = Moneda.USD.code(); }
                case 8 -> { from = Moneda.USD.code(); to = Moneda.CLP.code(); }
                case 9 -> { from = Moneda.CLP.code(); to = Moneda.PHP.code(); }
                case 10 -> { from = Moneda.PHP.code(); to = Moneda.CLP.code(); }
                case 11 -> { from = Moneda.PHP.code(); to = Moneda.USD.code(); }
                case 12 -> { from = Moneda.USD.code(); to = Moneda.PHP.code(); }
                default -> {
                    System.out.println("Opción no reconocida. Intente nuevamente.");
                    continue;
                }
            }

            System.out.print("Ingrese la cantidad a convertir: ");
            String cantidadLine = scanner.nextLine();
            double cantidad;
            try {
                cantidad = Double.parseDouble(cantidadLine.trim());
            } catch (NumberFormatException | InputMismatchException ex) {
                System.out.println("Cantidad inválida. Ingrese un número (por ejemplo 123.45).\n");
                continue;
            }

            try {
                double rate = consultaTasa.getRate(from, to);
                double resultado = cantidad * rate;
                System.out.printf("%.2f %s = %.2f %s%n", cantidad, from, resultado, to);
            } catch (IllegalStateException e) {
                System.out.println("Error de configuración o datos: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error de red al consultar la API: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("La consulta fue interrumpida.");
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("======================================");
        System.out.println("     Conversor de Monedas - Menú      ");
        System.out.println("======================================");
        System.out.println("1)  Dólar (USD) a Peso Argentino (ARS)");
        System.out.println("2)  Peso Argentino (ARS) a Dólar (USD)");
        System.out.println("3)  Dólar (USD) a Real Brasileño (BRL)");
        System.out.println("4)  Real Brasileño (BRL) a Dólar (USD)");
        System.out.println("5)  Dólar (USD) a Peso Colombiano (COP)");
        System.out.println("6)  Peso Colombiano (COP) a Dólar (USD)");
        System.out.println("7)  Peso Chileno (CLP) a Dólar (USD)");
        System.out.println("8)  Dólar (USD) a Peso Chileno (CLP)");
        System.out.println("9)  Peso Chileno (CLP) a Peso Filipino (PHP)");
        System.out.println("10) Peso Filipino (PHP) a Peso Chileno (CLP)");
        System.out.println("11) Peso Filipino (PHP) a Dólar (USD)");
        System.out.println("12) Dólar (USD) a Peso Filipino (PHP)");
        System.out.println("13) Salir");
    }

    public static void main(String[] args) {
        Principal app = new Principal();
        app.run();
    }
}
