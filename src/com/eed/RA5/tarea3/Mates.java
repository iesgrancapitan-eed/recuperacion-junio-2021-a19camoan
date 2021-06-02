package ejercicio5_3;

public class Mates extends Fraccion{

  int numerador;
  int denominador;

  public Mates(int numerador) {
    super(numerador);
  }

  public Fraccion suma(Fraccion f) {
    return new Fraccion (((this.numerador * f.denominador) + (this.denominador * f.numerador)) , (this.denominador * f.denominador));
  }

}