package com.eed.RA4.tarea44.gestisimal.excepciones;

/**
 * Lanzado para indicar que el artículo no existe
 * 
 * @author sergio Vera Jurado
 *
 */
public class ArticuloInexistenteException extends Exception {

  /**
   * crea la excepción ArticuloInexistenteException
   * 
   * @param string indica la descripción del error
   */
  public ArticuloInexistenteException(String string) {
    super(string);
  }


  /**
   * Generamos serialVersionUID para evitar un InvalidClassException
   */
  private static final long serialVersionUID = -2308106173277932565L;

}
