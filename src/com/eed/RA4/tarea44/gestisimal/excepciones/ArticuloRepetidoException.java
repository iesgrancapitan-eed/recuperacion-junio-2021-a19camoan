package com.eed.RA4.tarea44.gestisimal.excepciones;

/**
 * Lanzado para indicar que el artículo está repetido
 * 
 * @author Sergio Vera Jurado
 *
 */
public class ArticuloRepetidoException extends Exception {

  /**
   * Generamos serialVersionUID para evitar un InvalidClassException
   */
  private static final long serialVersionUID = -5275867970911297179L;

  /**
   * crea la excepción ArticuloRepetidoException
   * 
   * @param string indica la descripción del error
   */
  public ArticuloRepetidoException(String string) {
    super(string);
  }

}
