package com.eed.RA4.tarea44.gestisimal.excepciones;

/**
 * Lanzado para indicar que se produce un error al usar el xml del almacén
 * 
 * @author Sergio Vera Jurado
 *
 */
public class AlmacenXMLException extends Exception {

  /**
   * Generamos serialVersionUID para evitar un InvalidClassException
   */
  private static final long serialVersionUID = 1689646165076599118L;

  /**
   * crea la excepción AlmacenXMLException
   * 
   * @param string indica la descripción del error
   */
  public AlmacenXMLException(String string) {
    super(string);
  }

}
