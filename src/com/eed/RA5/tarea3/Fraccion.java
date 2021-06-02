package ejercicio5_3;

import java.util.Scanner;

public class Fraccion {

  Scanner s = new Scanner(System.in);

  // Atributos (= variables en POO).
  protected int numerador;
  protected int denominador;


  // Constructor.
  public Fraccion (int numerador, int denominador) {
    this.numerador = numerador;
    this.denominador = denominador;
  }

  public Fraccion (int numerador) {
    this.numerador = numerador;
    this.denominador = 1;
  }

  // Getters.
  public int getDenominador() {
    return denominador;
  }

  public int getNumerador() {
    return numerador;
  }

  // Setters.
  public void setDenominador(int denominador) {
    while (denominador == 0) {
      System.out.println("Introduzca otro denominador: ");
      denominador = s.nextInt();
    }

    this.denominador = denominador;

    s.close();
  }

  public void setNumerador(int numerador) {
    this.numerador = numerador;
  }

  // Método toString.
  public String toString() {
    return " " + this.numerador + "\n" + 
        "----" + "\n" + 
        " " + this.denominador + "\n";
  }

  // Da el resultado de una fraccion.
  public double result() {
    return (double)(this.numerador / this.denominador);
  }

  // Multiplicacion de dos fracciones, devuelve otro objeto fraccion.
  public Fraccion multiplicacion(Fraccion f) {
    return new Fraccion (this.numerador * f.numerador, this.denominador * f.denominador);
  }

  // Suma de dos fracciones, devuelve otro objeto fraccion.
  public Fraccion suma(Fraccion f) {
    return new Fraccion (((this.numerador * f.denominador) + (this.denominador * f.numerador)) , (this.denominador * f.denominador));
  }

  // Resta de dos fracciones, devuelve otro objeto fraccion.
  public Fraccion resta(Fraccion f) {
    return new Fraccion (((this.numerador * f.denominador) - (this.denominador * f.numerador)) , (this.denominador * f.denominador));
  }

  // Cálculo de Maximo Comun Divisor para la posterior simplificacion.
  static int mcd(int a, int b) {
    a = Math.abs(a);
    b = Math.abs(b);

    while (b > 0) {
      int r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  // Simplificacion.
  public void simplifica(Fraccion f) {
    int mcd = Fraccion.mcd(this.numerador, this.denominador);

    this.numerador /= mcd;
    this.denominador /= mcd;
  }

}
