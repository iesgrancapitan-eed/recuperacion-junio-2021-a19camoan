package com.eed.RA4.tarea44.gestisimal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import com.eed.RA4.tarea44.gestisimal.excepciones.AlmacenCSVException;
import com.eed.RA4.tarea44.gestisimal.excepciones.ArticuloInexistenteException;
import com.eed.RA4.tarea44.gestisimal.excepciones.ArticuloRepetidoException;
import com.eed.RA4.tarea44.gestisimal.excepciones.CodigoNoValidoException;
import com.eed.RA4.tarea44.gestisimal.excepciones.NumeroNegativoException;

/**
 * Clase Almacen que realice el alta, baja, modificacion, entrada de mercancía (incrementa
 * unidades), salida de mercancía (decrementa unidades). El estado será un ArrayList de artículos.
 * Esta clase es un envoltorio de un ArrayList. Su comportamiento será: añadir artículos (no puede
 * haber dos artículos iguales), eliminar artículos, incrementar las existencias de un artículo (se
 * delega en la clase Articulo), decrementar las existencias de un artículo (nunca por debajo de
 * cero, se delega en la clase Articulo), devolver un artículo (para mostrarlo). Para listar el
 * almacén podra devolverse una cadena con todos los artículos del almacén (toString).
 * 
 * @author Sergio Vera Jurado
 *
 */
public class Almacen {
  /**
   * cabecera del csv que se debe cumplir para que el csv sea válido
   */
  private static final String CSV_CABECERA =
      "Descripcion,Precio compra,Precio venta,Numero unidades,Stock maximo,Stock seguridad";
  /**
   * objeto almacén en el que se guardan los artículos
   */
  private static ArrayList<Articulo> almacen = new ArrayList<Articulo>();

  /**
   * Da de alta un articulo
   * 
   * @param precioCompra precioCompra El precio de compra de un artículo
   * @param precioVenta El precio de venta de un artículo
   * @param descripcion Descripción de un artículo
   * @param numeroUnidades Cantidades disponibles del nuevo artículo
   * @param stockSeguridad Stock mínimo permitido de un nuevo artículo
   * @param stockMaximo Stock máximo permitido de un nuevo artículo
   * @throws NumeroNegativoException Si el número de unidades o el precio de compra es negativo
   * @throws ArticuloRepetidoException Si el artículo tiene el mismo código que otro ya creado
   */

  void alta(double precioCompra, double precioVenta, String descripcion, int numeroUnidades,
      int stockSeguridad, int stockMaximo)
      throws NumeroNegativoException, ArticuloRepetidoException {
    Articulo articulo = new Articulo(precioCompra, precioVenta, descripcion, numeroUnidades,
        stockSeguridad, stockMaximo);
    if (!almacen.contains(articulo))
      almacen.add(articulo);
    else
      throw new ArticuloRepetidoException("El articulo introducido ya existe");

  }

  /**
   * Elimina un artículo previamente introducido pasándole el código
   * 
   * @param codigo sirve para identificar un artículo
   * @return True si la operación se ha realizado correctamente
   * @throws CodigoNoValidoException Si el código introducido no existe
   */

  boolean eliminar(int codigo) throws CodigoNoValidoException {
    return almacen.remove(new Articulo(codigo));

  }

  /**
   * busca un artículo y llama al método incrementar de la clase artículo
   * 
   * @see Articulo
   * @param cantidad el número por el que se va a incrementar el artículo
   * @param codigo el código del artículo que se va a incrementar
   * @throws NumeroNegativoException Si el número de unidades o el precio de compra es negativo
   * @throws ArticuloInexistenteException si el código introducido no se encuentra
   */
  void incrementarCantidad(int cantidad, int codigo)
      throws NumeroNegativoException, ArticuloInexistenteException {
    buscarArticulo(codigo).incrementar(cantidad);


  }

  /**
   * busca un artículo y llama al método decrementar de la clase artículo
   * 
   * @see Articulo
   * @param cantidad la cantidad en la que se va a decrementar
   * @param codigo para buscar un articulo en base a su código
   * @throws NumeroNegativoException Si el número de unidades o el precio de compra es negativo
   * @throws ArticuloInexistenteException si el código introducido no se encuentra
   */
  void decrementarCantidad(int cantidad, int codigo)
      throws NumeroNegativoException, ArticuloInexistenteException {
    buscarArticulo(codigo).decrementar(cantidad);
  }

  /**
   * Devuelve la posición del artículo en el arraylist según el código indicado
   * 
   * @param codigo para buscar el artículo
   * @throws ArticuloInexistenteException si el código del artículo no existe
   * @return el Artículo encontrado
   */
  Articulo buscarArticulo(int codigo) throws ArticuloInexistenteException {
    try {
      return almacen.get(almacen.indexOf((new Articulo(codigo))));
    } catch (IndexOutOfBoundsException e) {
      throw new ArticuloInexistenteException(
          "El codigo introducido no pertenece a ningun artículo");
    }
  }

  /**
   * Representación en cadena del objeto almacén
   */
  @Override
  public String toString() {
    return "[almacen=" + almacen + "]";
  }

  /**
   * guarda los datos introducidos en un archivo CSV
   * 
   * @param fileName Nombre del archivo
   * @throws IOException Señala que se ha producido una excepción de E/S de algún tipo.
   */
  void guardarCSV(String fileName) throws IOException {
    var file = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.CREATE);
    guardarCabeceraCSV(file);
    for (Articulo articulo : almacen) {
      guardarArticuloCSV(articulo, file);
    }
    file.close();
  }

  /**
   * escribe la cabecera del CSV
   * 
   * @param file Nombre del archivo
   * @throws IOException Señala que se ha producido una excepción de E/S de algún tipo.
   */
  private void guardarCabeceraCSV(BufferedWriter file) throws IOException {
    file.write(CSV_CABECERA);
    file.newLine();
  }

  /**
   * guarda las distintas variables de los artículos
   * 
   * @param articulo objeto de la clase artículo
   * @param file nombre del archivo
   * @throws IOException Señala que se ha producido una excepción de E / S de algún tipo.
   */
  private void guardarArticuloCSV(Articulo articulo, BufferedWriter file) throws IOException {
    file.write("\"" + articulo.getDescripcion() + "\",");
    file.write("\"" + articulo.getPrecioCompra() + "\",");
    file.write("\"" + articulo.getPrecioVenta() + "\",");
    file.write("\"" + articulo.getNumeroUnidades() + "\",");
    file.write("\"" + articulo.getStockMaximo() + "\",");
    file.write("\"" + articulo.getStockSeguridad() + "\"");
    file.newLine();
  }

  /**
   * carga un archivo csv previamente especificado
   * 
   * @param fileName nombre del archivo
   * @throws IOException Señala que se ha producido una excepción de E / S de algún tipo.
   * @throws AlmacenCSVException si no se encuentra el csv
   * @throws NumeroNegativoException si el número es inferior a 0
   */
  static void cargarCSV(String fileName)
      throws AlmacenCSVException, NumeroNegativoException, IOException {
    var file = Files.newBufferedReader(Paths.get(fileName));
    validadCabeceraCSV(file);

    String line;
    while ((line = file.readLine()) != null) {
      Articulo articulo = nuevoArticuloCSV(line);
      almacen.add(articulo);
    }
    file.close();
  }

  /**
   * valida la cabecera del csv que se va ha cargar
   * 
   * @param file nombre del archivo
   * @throws IOException Señala que se ha producido una excepción de E / S de algún tipo.
   * @throws AlmacenCSVException si no se encuentra el csv
   */
  private static void validadCabeceraCSV(BufferedReader file)
      throws IOException, AlmacenCSVException {
    String cabecera = file.readLine().trim();
    if (!cabecera.equalsIgnoreCase(CSV_CABECERA)) {
      throw new AlmacenCSVException("Cabecera errónea en el CSV.");
    }
  }

  /**
   * lee el csv introducido y crea el artículo correspondiente
   * 
   * @param line línea que se escribe
   * @return articulo artículo que se escribe
   * @throws AlmacenCSVException si no se encuentra el csv
   * @throws NumeroNegativoException si el número es inferior a 0
   */
  private static Articulo nuevoArticuloCSV(String line)
      throws AlmacenCSVException, NumeroNegativoException {
    validarArticulosCSV(line);
    String[] guardarArticulos = line.split("\",");

    String descripcion = guardarArticulos[0].replace("\"", "");
    double precioCompra = Double.parseDouble(guardarArticulos[1].replace("\"", ""));
    double precioVenta = Double.parseDouble(guardarArticulos[2].replace("\"", ""));
    int numeroUnidades = Integer.parseInt(guardarArticulos[3].replace("\"", ""));
    int stockMaximo = Integer.parseInt(guardarArticulos[4].replace("\"", ""));
    int stockSeguridad = Integer.parseInt(guardarArticulos[5].replace("\"", ""));

    return new Articulo(precioCompra, precioVenta, descripcion, numeroUnidades, stockSeguridad,
        stockMaximo);

  }

  /**
   * valida que las variables del csv sean las adecuadas
   * 
   * @param line linea en la que se escribe
   * @throws AlmacenCSVException si no se encuentra el csv
   */
  private static void validarArticulosCSV(String line) throws AlmacenCSVException {
    int numeroDeCampos = line.split("\",").length;
    int numeroDeCamposHead = CSV_CABECERA.split(",").length;

    if (numeroDeCampos != numeroDeCamposHead) {
      throw new AlmacenCSVException(
          line + ": no es un formato válido para convertirlo en Articulo.");
    }
  }
}

