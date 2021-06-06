package com.eed.RA4.javadoc;

/**
 * Crea una clase Fraccion de forma que podamos hacer las siguientes operaciones:
 *
 *  ·Contruir un objeto Fraccion pasándole el numerador y el denominador.
 *  ·Obtener la fracción como cadena de caracteres.
 *  ·Obtener y modificar numerador y denominador. No se puede dividir por cero.
 *  ·Obtener resultado de la fracción (número real).
 *  ·Multiplicar la fracción por un número (el resultado es otro objeto fracción).
 *  ·Multiplicar, sumar y restar fracciones (el resultado es otro objeto fracción).
 *  ·Simplificar la fracción (cambia el objeto actual).

 * @author Andrés Castillero Moriana
 * 
 */
public class Fraccion {

	/**
	 * Numerador de una fracción o división
	 */
	protected int numerador;
	
	/**
	 * Denominador de una fracción o división
	 */
	protected int denominador;

	/**
	 * Crea un objeto Fraccion al pasarle un numerador y un denominador
	 * @param numerador Numerador de una fracción o división
	 * @param denominador Denominador de una fracción o división
	 */
	public Fraccion (int numerador, int denominador) {
		this.numerador = numerador;
		this.denominador = denominador;
	}

	/**
	 * Crea un objeto Fraccion al pasarle solo un número. El numerador será el número introducido y el denominador 1.
	 * @param numerador
	 */
	public Fraccion (int numerador) {
		this.numerador = numerador;
		this.denominador = 1;
	}

	/**
	 * Devuelve denominador
	 * @return int denominador
	 */
	public int getDenominador() {
		return denominador;
	}

	/**
	 * Devuelve numerador
	 * @return int numerador
	 */
	public int getNumerador() {
		return numerador;
	}

	/**
	 * establece la variable denominador de la fracción
	 * @param denominador
	 * @throws DenominadorCeroException
	 */
	public void setDenominador(int denominador) throws DenominadorCeroException {
		if(this.denominador == 0) {
			throw new DenominadorCeroException("El denominador no puede ser 0.");
		}
		this.denominador = denominador;
	}

	/**
	 * establece la variable numerador de la fracción
	 * @param numerador
	 */
	public void setNumerador(int numerador) {
		this.numerador = numerador;
	}

	/**
	 * Visualización del objeto fracción
	 */
	public String toString() {
		return " " + this.numerador + "\n" + 
				"----" + "\n" + 
				" " + this.denominador + "\n";
	}

	/**
	 * Este método devuelve el resultado de la división entre numerador y denominador
	 * @return double Divide numerador entre denominador
	 */
	public double result() {
		return (double)(this.numerador / this.denominador);
	}

	/**
	 * Este método devuelve el resultado de la multiplicación entre numerador y denominador
	 * @param f
	 * @return Fraccion Objeto fracción con el valor de la multiplicación de las 2 fracciones que se pasan como parámetros
	 */
	public Fraccion multiplicacion(Fraccion f) {
		return new Fraccion (this.numerador * f.numerador, this.denominador * f.denominador);
	}

	/**
	 * Este método devuelve el resultado de la suma entre numerador y denominador
	 * @param f
	 * @return Fraccion Objeto fracción con el valor de la suma de las 2 fracciones que se pasan como parámetros
	 */
	public Fraccion suma(Fraccion f) {
		return new Fraccion (((this.numerador * f.denominador) + (this.denominador * f.numerador)) , (this.denominador * f.denominador));
	}

	/**
	 * Este método devuelve el resultado de la resta entre numerador y denominador
	 * @param f
	 * @return Fraccion Objeto fracción con el valor de la resta de las 2 fracciones que se pasan como parámetros
	 */
	public Fraccion resta(Fraccion f) {
		return new Fraccion (((this.numerador * f.denominador) - (this.denominador * f.numerador)) , (this.denominador * f.denominador));
	}

	/**
	 * Calcula el mínimo común divisor de los 2 número de la fracción y lo almacena
	 * @param a Numerador
	 * @param b Denominador
	 * @return mcd El mínimo común divisor
	 */
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

	/**
	 * Simplifica la fracción
	 * @param f
	 */
	public void simplifica(Fraccion f) {
		int mcd = Fraccion.mcd(this.numerador, this.denominador);

		this.numerador /= mcd;
		this.denominador /= mcd;
	}

}
