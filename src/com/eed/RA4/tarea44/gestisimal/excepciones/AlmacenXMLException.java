package com.eed.RA4.tarea44.gestisimal.excepciones;
/**
 * Si da error al usar el xml del almacén
 * @author Sergio Vera Jurado
 *
 */
public class AlmacenXMLException extends Exception {

	/**
	 * Generamos serialVersionUID para evitar un InvalidClassException
	 */
	private static final long serialVersionUID = 1689646165076599118L;

	/**
	 * genera AlmacenXMLException
	 * @param string
	 */
	public AlmacenXMLException(String string) {
		super(string);
	}

}
