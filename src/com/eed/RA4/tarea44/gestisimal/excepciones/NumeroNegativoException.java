package com.eed.RA4.tarea44.gestisimal.excepciones;

/**
 * Lanzado para indicar que número es negativo
 * 
 * @author Sergio Vera Jurado
 * 
 */
public class NumeroNegativoException extends Exception {

  /**
   * Generamos serialVersionUID para evitar un InvalidClassException
   */
  private static final long serialVersionUID = -7619411719334351922L;

  /**
   * crea la excepción NumeroNegativoException
   * 
   * @param string indica la descripción del error
   */
  public NumeroNegativoException(String string) {
    super(string);
  }

}
