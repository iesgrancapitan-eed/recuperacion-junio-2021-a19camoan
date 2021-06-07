package com.eed.RA4.tarea44.gestisimal;

import com.eed.RA4.tarea44.gestisimal.excepciones.NumeroNegativoException;

/**
 * Clase Articulo que representa a los artículos del almacén. Su estado sera: codigo, descripción,
 * precio de compra, precio de venta, número de unidades (nunca negativas), stock de seguridad y
 * stock máximo. Como comportamiento: Consideramos que el código va a generarse de forma automática
 * en el constructor, así no puede haber dos artículos con el mismo código. Esto implica que no
 * puede modificarse el código de un artículo, pero si el resto de las propiedades. Podremos mostrar el
 * artículo, por lo que necesita una representación del artículo en forma de cadena (toString).
 * @author Sergio Vera Jurado
 * @version 1.1
 * 
 */
public class Articulo {

	/**
	 * Incremento que permite identificar al artículo de forma única
	 */
	private static int codigoGenerar = 0;

	/**
	 * Identifica al artículo
	 */
	private int codigo;

	/**
	 * Precio al que se compra un artículo
	 */
	private double precioCompra;

	/**
	 * Precio al que se vende un artículo
	 */
	private double precioVenta;

	/**
	 * Pequeña descripción del artículo
	 */
	private String descripcion;

	/**
	 * Número de unidades disponibles de un artículo
	 */
	private int numeroUnidades;

	/**
	 * Stock mínimo de un artículo (No es obligatorio que sea inferior al numero de unidades)
	 */
	private int stockSeguridad;

	/**
	 * Stock máximo de un artículo (No es obligatorio que sea inferior al numero de unidades)
	 */
	private int stockMaximo;

	/**
	 * Construtor de la clase Articulo
	 * @param precioCompra El precio de compra de un artículo
	 * @param precioVenta El precio de venta de un artículo
	 * @param descripcion Descripción de un artículo
	 * @param numeroUnidades Cantidades disponibles del nuevo artículo
	 * @param stockSeguridad Stock mínimo permitido de un nuevo artículo
	 * @param stockMaximo Stock máximo permitido de un nuevo artículo
	 * @throws NumeroNegativoException Si el numero de unidades o el precio de compra es negativo
	 */
	Articulo(double precioCompra, double precioVenta, String descripcion, int numeroUnidades,
			int stockSeguridad, int stockMaximo) throws NumeroNegativoException {
		setCodigo();
		setPrecioCompra(precioCompra);
		setPrecioVenta(precioVenta);
		setDescripcion(descripcion);
		setStockSeguridad(stockSeguridad);
		setStockMaximo(stockMaximo);
		setNumeroUnidades(numeroUnidades);
	}

	/**
	 * Constructor de la clase artículo cuando solo especificamos el código
	 * @param codigo número entero para identificar un artículo
	 */
	Articulo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * devulve codigo
	 * @return codigo
	 */
	int getCodigo() {
		return codigo;
	}

	/**
	 * establece la variable código del artículo
	 */
	private void setCodigo() {
		codigoGenerar += 1;
		this.codigo = codigoGenerar;
	}

	/**
	 * devuelve precioCompra
	 * @return double precioCompra
	 */
	double getPrecioCompra() {
		return precioCompra;
	}

	/**
	 * Establece el precio de compra del artículo
	 * @param precioCompra Lo que le cuesta un artículo al almacen
	 * @throws NumeroNegativoException Cuando el número introducido es negativo
	 */
	private void setPrecioCompra(double precioCompra) throws NumeroNegativoException {
		if (precioCompra < 0)
			throw new IllegalArgumentException("El precio de compra no puede ser negativo");
		this.precioCompra = precioCompra;
	}

	/**
	 * devuelve precioVenta
	 * @return precioVenta
	 */
	double getPrecioVenta() {
		return precioVenta;
	}

	/**
	 * Establece el precio de venta de un artículo
	 * @param precioVenta cantidad que se recibe por un artículo
	 */
	private void setPrecioVenta(double precioVenta) {
		if (precioVenta < 0)
			throw new IllegalArgumentException("El precio de venta no puede ser negativo");
		this.precioVenta = precioVenta;
	}

	/**
	 * devuelve descripcion
	 * @return descripcion
	 */
	String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción de un artículo
	 * @param descripcion cadena con una breve explicación sobre el producto
	 */
	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * devuelve numeroUnidades
	 * @return numeroUnidades
	 */
	int getNumeroUnidades() {
		return numeroUnidades;
	}

	/**
	 * establece el número de unidades
	 * @param cantidad numero de existencias de un artículo
	 * @throws NumeroNegativoException cuando la cantidad pasada es un número negativo
	 */
	private void setNumeroUnidades(int cantidad) throws NumeroNegativoException {
		if (cantidad < 0) {
			throw new IllegalArgumentException("No puedes introducir una cantidad inferior a cero");
		}
		this.numeroUnidades = cantidad;
	}

	/**
	 * devuelve stockSeguridad
	 * @return stockSeguridad
	 */
	int getStockSeguridad() {
		return stockSeguridad;
	}

	/**
	 * establece el stock de seguridad
	 * @param stockSeguridad cantidad minima que debe haber de un artículo
	 */
	private void setStockSeguridad(int stockSeguridad) {
		this.stockSeguridad = stockSeguridad;
	}

	/**
	 * devuelve stockMaximo
	 * @return stockMaximo
	 */
	int getStockMaximo() {
		return stockMaximo;
	}

	/**
	 * Establece el stock máximo
	 * @param stockMaximo cantidad máxima que debe haber de un artículo
	 */
	private void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	/**
	 * Este metodo incrementa el numero de unidades de un artículo
	 * 
	 * @param cantidadAIncrementar la cantidad en la que se va a incrementar el valor
	 * @throws NumeroNegativoException si la cantidad en la que se va a aumentar es negativa
	 */

	void incrementar(int cantidadAIncrementar) throws NumeroNegativoException {
		if (cantidadAIncrementar < 0)
			throw new NumeroNegativoException("No puedes incrementar usando numeros negativos");
		setNumeroUnidades(getNumeroUnidades() + cantidadAIncrementar);
	}

	/**
	 * Decrementa las unidades disponibles de un artículo
	 * 
	 * @param cantidadADecrementar la cantidad en la que se va a decrementar el valor
	 * @throws NumeroNegativoException si el número con el que se va a decrementar es negativo
	 */
	void decrementar(int cantidadADecrementar) throws NumeroNegativoException {
		if (cantidadADecrementar < 0)
			throw new NumeroNegativoException("No puedes decrementar usando numeros negativos");
		setNumeroUnidades(getNumeroUnidades() - cantidadADecrementar);
	}

	/**
	 * To string del artículo
	 */
	@Override
	public String toString() {
		return "Articulo [codigo=" + codigo + ", precioCompra=" + precioCompra + ", precioVenta="
				+ precioVenta + ", descripcion=" + descripcion + ", numeroUnidades=" + numeroUnidades
				+ ", stockSeguridad=" + stockSeguridad + ", stockMaximo=" + stockMaximo + "]";
	}

	/**
	 * hashCode del artículo
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	/**
	 * equals de artículo, 2 artículo son iguales si tienen el mismo código
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
}