package com.eed.RA4.tarea44.gestisimal.excepciones;

/**
 * Si el código no es válido
 * @author Sergio Vera Jurado
 *
 */
public class CodigoNoValidoException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 4954135465152486030L;

  public CodigoNoValidoException(String string) {
    super(string);
  }
}
