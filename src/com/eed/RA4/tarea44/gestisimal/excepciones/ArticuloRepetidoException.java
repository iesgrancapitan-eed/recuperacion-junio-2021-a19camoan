package com.eed.RA4.tarea44.gestisimal.excepciones;

/**
 * Si el artículo está repetido
 * @author Sergio Vera Jurado
 *
 */
public class ArticuloRepetidoException extends Exception {

	/**
	 * Generamos serialVersionUID para evitar un InvalidClassException
	 */
	private static final long serialVersionUID = -5275867970911297179L;

	/**
	 * genera ArticuloRepetidoException
	 * @param string
	 */
	public ArticuloRepetidoException(String string) {
		super(string);
	}

}
