package com.eed.RA4.javadoc;

/**
 * Si el denominador es 0.
 * @author Andrés Castillero Moriana
 *
 */
public class DenominadorCeroException extends Exception {

  /**
   * genera DenominadorCeroException
   * @param string
   */
  public DenominadorCeroException(String string) {
  }

  /**
   * Generamos serialVersionUID para evitar un InvalidClassException
   */
  private static final long serialVersionUID = -6014647145865500836L;
}
