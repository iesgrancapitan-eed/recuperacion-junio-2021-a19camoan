package com.eed.RA4.tarea44.gestisimal.excepciones;

/**
 * Lanzado para indicar que se produce un error al usar el csv del almacén
 * 
 * @author Sergio Vera Jurado
 *
 */
public class AlmacenCSVException extends Exception {

  /**
   * Generamos serialVersionUID para evitar un InvalidClassException
   */
  private static final long serialVersionUID = 4760983661709172079L;

  /**
   * crea la excepción AlmacenCSVException
   * 
   * @param string indica la descripción del error
   */
  public AlmacenCSVException(String string) {
    super(string);
  }

}
