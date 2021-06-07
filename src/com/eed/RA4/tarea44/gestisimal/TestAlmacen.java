package com.eed.RA4.tarea44.gestisimal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import com.eed.RA4.tarea44.gestisimal.excepciones.AlmacenCSVException;
import com.eed.RA4.tarea44.gestisimal.excepciones.AlmacenXMLException;
import com.eed.RA4.tarea44.gestisimal.excepciones.ArticuloInexistenteException;
import com.eed.RA4.tarea44.gestisimal.excepciones.ArticuloRepetidoException;
import com.eed.RA4.tarea44.gestisimal.excepciones.CodigoNoValidoException;
import com.eed.RA4.tarea44.gestisimal.excepciones.NumeroNegativoException;
import com.eed.RA4.tarea44.utiles.Menu;

/**
 * Clase test para el almacén
 * @author Sergio Vera Jurado
 *
 */
public class TestAlmacen {

	public static void main(String[] args) throws NumeroNegativoException, ArticuloRepetidoException,
	CodigoNoValidoException, ArticuloInexistenteException, FileNotFoundException {

		int opcion;
		Menu menu = new Menu("Almacen", "Alta de un articulo", "Incrementar articulo",
				"Decrementar existencias", "Mostrar articulo", "listar almacen", "eliminar art�culo",
				"Exportar CSV", "Importar CSV", "Exportar XML", "Importar XML", "Salir");

		Almacen almacen = new Almacen();
		/**
		 * Inicio del menu en el que se muestran las distintas opciones
		 */
		do {
			opcion = menu.elegir();

			try {
				switch (opcion) {
				case 1:
					almacen.alta(pedirPrecioCompra(), pedirPrecioVenta(), pedirDescripcion(),
							pedirUnidades(), pedirStockSeguridad(), pedirStockMaximo());
					System.out.println("Operación realizada correctamente");
					break;
				case 2:
					almacen.incrementarCantidad(pedirCantidad(), pedircodigo());
					System.out.println("Operación realizada correctamente");
					break;
				case 3:
					almacen.decrementarCantidad(pedirCantidad(), pedircodigo());
					System.out.println("Operación realizada correctamente");
					break;
				case 4:
					System.out.println(almacen.buscarArticulo(pedircodigo()));
					break;
				case 5:
					System.out.println(almacen);
					break;
				case 6:
					System.out.println(almacen.eliminar((pedircodigo())));
					break;
				case 7:
					System.out.println("Esta opción sobrescribre el archivo si ya existe");
					almacen.guardarCSV(pedirNombre());
					System.out.println("El CSV se ha guardado correctamente");
					break;
				case 8:
					Almacen.cargarCSV(pedirNombre());
					System.out.println("El CSV se ha importado correctamente");
					break;
				case 9:
					System.out.println("Esta opción sobrescribre el archivo si ya existe");
					almacen.guardarXML(pedirNombre());
					System.out.println("El XML se ha guardado correctamente");
					break;
				case 10:
					Almacen.cargarXml(pedirNombre());
					System.out.println("El XML se ha importado correctamente");
					break;
				case 11:
					System.out.println("Programa finalizado");
				}
			} catch (NumeroNegativoException | IllegalArgumentException | AlmacenXMLException
					| ArticuloRepetidoException | CodigoNoValidoException | ArticuloInexistenteException
					| AlmacenCSVException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println("El archivo especificado no ha sido encontrado");
			}

		} while (opcion != 11);

	}
	/**
	 * pide un nombre para saber qué archivo tiene que usar
	 * para leer los datos
	 * @return nombre del archivo
	 */
	private static String pedirNombre() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el nombre del archivo: ");
		return s.nextLine();
	}
	
	/**
	 * pide el stock máximo
	 * @return int
	 */
	private static int pedirStockMaximo() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el stock maximo: ");
		return s.nextInt();
	}
	
	/**
	 * pide el stock de seguridad
	 * @return int
	 */
	private static int pedirStockSeguridad() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el stock de seguridad: ");
		return s.nextInt();
	}
	
	/**
	 * pide el número de unidades
	 * @return int
	 */
	private static int pedirUnidades() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el numero de unidades: ");
		return s.nextInt();
	}
	/**
	 * pide la descripcion de un artículo
	 * @return string
	 */
	private static String pedirDescripcion() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce la descripción del articulo: ");
		return s.nextLine();
	}
	/**
	 * pide el precio de compra de un artículo
	 * @return int
	 */
	private static double pedirPrecioCompra() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el precio de compra del art�culo: ");
		return s.nextDouble();
	}
	
	/**
	 * pide el precio de venta de un artículo
	 * @return int
	 */
	private static double pedirPrecioVenta() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el precio de venta del art�culo: ");
		return s.nextDouble();
	}
	
	/**
	 * pide el codigo de un artículo
	 * @return int
	 */
	private static int pedircodigo() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el codigo del artículo :");
		return s.nextInt();
	}

	/**
	 * pide la cantidad de existencias de un artículo
	 * @return int
	 */
	private static int pedirCantidad() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce la cantidad :");
		return s.nextInt();
	}
}
