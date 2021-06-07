package com.eed.RA4.tarea44.gestisimal.excepciones;
/**
 * Si da error al usar el csv del almacén
 * @author Sergio Vera Jurado
 *
 */
public class AlmacenCSVException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 4760983661709172079L;

  public AlmacenCSVException(String string) {
    super(string);
  }

}
