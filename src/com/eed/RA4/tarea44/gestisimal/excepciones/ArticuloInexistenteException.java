package com.eed.RA4.tarea44.gestisimal.excepciones;
/**
 * Si el artículo no existe
 * @author sergio Vera Jurado
 *
 */
public class ArticuloInexistenteException extends Exception {

	/**
	 * genera ArticuloInexistenteException
	 * @param string
	 */
	public ArticuloInexistenteException(String string) {
		super(string);
	}


	/**
	 * Generamos serialVersionUID para evitar un InvalidClassException
	 */
	private static final long serialVersionUID = -2308106173277932565L;

}
