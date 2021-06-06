/**
 * @author acm98
 */
package com.eed.RA3.tarea1;

import java.util.Scanner;

public class Video {

  public static void main(String[] args) {

    Scanner entrada = new Scanner(System.in);

    System.out.println("Introduzca un valor para el primer numero: ");
    int valor1 = entrada.nextInt();

    System.out.println("Introduzca un valor para el segundo numero: ");
    int valor2 = entrada.nextInt();

    System.out.println("El resultado de la suma es " + suma(valor1, valor2));
    System.out.println("El resultado de la resta es " + resta(valor1, valor2));
    System.out.println("El resultado de la multiplicacion es " + multiplicacion(valor1, valor2));
    System.out.println("El resultado de la division es " + division(valor1, valor2));

    entrada.close();

  }

  /**
   * Función que devuelve la suma de dos números
   * @param sumando1 Primer número
   * @param sumando2 Segundo número
   * @return Resultado
   */
  private static int suma (int sumando1, int sumando2) {
    return (sumando1 + sumando2);
  }

  /**
   * Función que devuelve la resta de dos números
   * @param minuendo Primer número
   * @param sustraendo Segundo número
   * @return Resultado
   */
  private static int resta (int minuendo, int sustraendo) {
    return (minuendo - sustraendo);
  }

  /**
   * Función que devuelve la multiplicación de dos números
   * @param numero1 Primer número
   * @param numero2 Segundo número
   * @return Resultado
   */
  private static int multiplicacion (int numero1, int numero2) {
    return (numero1 * numero2);
  }

  /**
   * Función que devuelve la división entre dos números
   * @param dividendo Primer número
   * @param divisor Segundo número
   * @return Resultado
   */
  private static double division (int dividendo, int divisor) {
    return (double) (dividendo / divisor);
  }

}

