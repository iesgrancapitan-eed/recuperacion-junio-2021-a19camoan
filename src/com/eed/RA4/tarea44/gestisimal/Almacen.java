package com.eed.RA4.tarea44.gestisimal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.eed.RA4.tarea44.gestisimal.excepciones.AlmacenCSVException;
import com.eed.RA4.tarea44.gestisimal.excepciones.AlmacenXMLException;
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
	 * busca un artículo y llama al metodo decrementar de la clase artículo
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
	 * Devuelve la posición del artículo en el arraylist según el código pasado
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
	 * toString del almacén
	 */
	@Override
	public String toString() {
		return "[almacen=" + almacen + "]";
	}

	/**
	 * guarda los datos introducidos en un archivo CSV
	 * @param fileName Nombre del archivo 
	 * @throws IOException
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
	 * @param file Nombre del archivo
	 * @throws IOException
	 */
	private void guardarCabeceraCSV(BufferedWriter file) throws IOException {
		file.write(CSV_CABECERA);
		file.newLine();
	}

	/**
	 * guarda las distintas variables de los artículos
	 * @param articulo objeto de la clase artículo
	 * @param file nombre del archivo
	 * @throws IOException
	 */
	private void guardarArticuloCSV(Articulo articulo, BufferedWriter file)
			throws IOException {
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
	 * @param fileName nombre del archivo
	 * @throws IOException
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
	 * @param file nombre del archivo
	 * @throws IOException
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

	/**
	 * guarda los artículos creados en un archivo xml
	 * @param fileName nombre del archivo
	 * @throws AlmacenXMLException si no se encuentra el xml
	 * @throws IOException
	 */
	void guardarXML(String fileName) throws AlmacenXMLException, IOException {
		try {
			Document xml = crearDocumentXML();
			guardarRootXML(xml);
			for (Articulo articulo: almacen) {
				guardarArticuloXML(articulo, xml);
			}

			guardarArchivoXML(xml, fileName);

		} catch (ParserConfigurationException | TransformerException e) {
			throw new AlmacenXMLException("Error al generar XML");
		}
	}

	/**
	 * crea el documento Xml
	 * @return el constructor del documento
	 * @throws ParserConfigurationException
	 */

	private Document crearDocumentXML() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		return document;
	}

	/**
	 * crea el elemento root en el xml
	 * @param xml
	 */

	private void guardarRootXML(Document xml) {
		Element root = xml.createElement("Almacen");
		xml.appendChild(root);
	}

	/**
	 * guarda las distintas variables del artículo en el xml
	 * @param articulo el articulo que se escribe
	 * @param xml el documento sobre el que se va a escribir
	 */

	private void guardarArticuloXML(Articulo articulo, Document xml) {
		Element root = xml.getDocumentElement();

		Element articuloElement = xml.createElement("Articulo");
		root.appendChild(articuloElement);

		guardarCampoArticulo("Descripcion", articulo.getDescripcion(), articuloElement);
		guardarCampoArticulo("PrecioCompra", String.valueOf(articulo.getPrecioCompra()),
				articuloElement);
		guardarCampoArticulo("PrecioVenta", String.valueOf(articulo.getPrecioVenta()),
				articuloElement);
		guardarCampoArticulo("NumeroUnidades", String.valueOf(articulo.getNumeroUnidades()),
				articuloElement);
		guardarCampoArticulo("StockMaximo", String.valueOf(articulo.getStockMaximo()),
				articuloElement);
		guardarCampoArticulo("StockSeguridad", String.valueOf(articulo.getStockSeguridad()),
				articuloElement);

	}

	/**
	 * crea el campo en el xml
	 * @param atributo el nombre del atributo
	 * @param valorCampo valor que se escribe
	 * @param articuloElement elemento de articulo
	 */

	private void guardarCampoArticulo(String atributo, String valorCampo, Element articuloElement) {
		Document xml = articuloElement.getOwnerDocument();
		Element attrElement = xml.createElement(atributo);
		attrElement.appendChild(xml.createTextNode(valorCampo));
		articuloElement.appendChild(attrElement);
	}

	/**
	 * guarda el xml con el nombre especificado
	 * @param xml documento
	 * @param fileName nombre del archivo
	 * @throws IOException
	 * @throws TransformerException
	 */

	private void guardarArchivoXML(Document xml, String fileName)
			throws IOException, TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource xmlSource = new DOMSource(xml);
		StreamResult output = new StreamResult(new FileWriter(fileName));
		transformer.transform(xmlSource, output);
	}

	/**
	 * carga el xml especificado
	 * @param fileName nombre del archivo
	 * @throws IOException
	 * @throws AlmacenXMLException si no se encuentra el xml
	 * @throws NumeroNegativoException si el numero es negativo
	 */

	static void cargarXml(String fileName)
			throws IOException, AlmacenXMLException, NumeroNegativoException {
		try {
			Document xml = nuevoDocumentoXML(fileName);
			NodeList articulos = xml.getElementsByTagName("Articulo");
			for (int i = 0; i < articulos.getLength(); i++) {
				Articulo articulo = nuevoArticuloXML(articulos.item(i));
				almacen.add(articulo);
			}
		} catch (ParserConfigurationException | SAXException e) {
			throw new AlmacenXMLException("Error al cargar XML ");
		}
	}

	/**
	 * crea el archivo xml
	 * @param fileName nombre del archivo
	 * @return documento
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */

	private static Document nuevoDocumentoXML(String fileName)
			throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(fileName));
		document.getDocumentElement().normalize();
		return document;
	}

	/**
	 * crea el articulo en base a los datos del xml
	 * @param itemArticulo variable del articulo que se va a leer
	 * @return un articulo nuevo
	 * @throws NumeroNegativoException si el numero es inferior a 0
	 */
	private static Articulo nuevoArticuloXML(Node itemArticulo) throws NumeroNegativoException {
		String descripcion = getCampoArticulo("Descripcion", itemArticulo);
		double precioCompra = Double.parseDouble(getCampoArticulo("PrecioCompra", itemArticulo));
		double precioVenta = Double.parseDouble(getCampoArticulo("PrecioVenta", itemArticulo));
		int numeroUnidades = Integer.parseInt(getCampoArticulo("NumeroUnidades", itemArticulo));
		int stockMaximo = Integer.parseInt(getCampoArticulo("StockMaximo", itemArticulo));
		int stockSeguridad = Integer.parseInt(getCampoArticulo("StockSeguridad", itemArticulo));

		return new Articulo(precioCompra, precioVenta, descripcion, numeroUnidades, stockSeguridad,
				stockMaximo);
	}

	/**
	 * obtiene los datos del nodo del xml
	 * @param field campo que se va a escribir
	 * @param itemArticulo nombre de la variable del artículo
	 * @return elemento
	 */

	private static String getCampoArticulo(String field, Node itemArticulo) {
		Element elementoArticulo = (Element) itemArticulo;
		return elementoArticulo.getElementsByTagName(field).item(0).getTextContent();
	}
}

