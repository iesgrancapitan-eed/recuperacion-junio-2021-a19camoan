package com.eed.RA4.tarea44.gestisimal.excepciones;

/**
 * Lanzado para indicar que el código no es válido
 * 
 * @author Sergio Vera Jurado
 *
 */
public class CodigoNoValidoException extends Exception {

  /**
   * Generamos serialVersionUID para evitar un InvalidClassException
   */
  private static final long serialVersionUID = 4954135465152486030L;

  /**
   * crea la excepción CodigoNoValidoException
   * 
   * @param string indica la descripción del error
   */

  public CodigoNoValidoException(String string) {
    super(string);
  }
}
