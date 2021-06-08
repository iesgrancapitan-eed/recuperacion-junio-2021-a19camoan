package com.eed.RA4.javadoc;

/**
 * Lanzado para indicar que el denominador es 0.
 * @author Andrés Castillero Moriana
 *
 */
public class DenominadorCeroException extends Exception {

  /**
   * crea la excepcion DenominadorCeroException
   * @param string indica la descripcion del error
   */
  public DenominadorCeroException(String string) {
	  super(string);
  }

  /**
   * Generamos serialVersionUID para evitar un InvalidClassException
   */
  private static final long serialVersionUID = -6014647145865500836L;
}
