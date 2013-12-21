package printSystem;

import java.io.Serializable;
import dataStructures.Entry;
import dataStructures.Iterator;

public interface PrintManager extends Serializable {

	/**
	 * Add a photo to the system
	 * The code, Name, width and height of the photo must be provided
	 * @param code
	 * @param fileName
	 * @param width
	 * @param height
	 */
	void addPhoto(String code, String fileName, int width, int height);
	/**
	 * Add an empty album to the system
	 * The album code must be provided
	 * @param code
	 */
	void addAlbum(String code);

	/**
	 * Add a photo to an album with the width and the height for impression
	 * The width and height for impression must be lower than the original photo
	 * The photo and the album must be in the system
	 * @param code
	 * @param albumCode
	 * @param width
	 * @param height
	 */
	void addPhotoToAlbum(String code, String albumCode, int width, int height);

	/**
	 *
	 *Add a printer to the system
	 *The name, location, max width, max height, max Impressions and Maintenance Value must be provided
	 *The max width and height will set the printer limits
	 *The max impressions is the maximum number of prints that the printer can do before maintenance
	 *The maintenance value is the value controls when the printer needs maintenance
	 * @param name
	 * @param location
	 * @param maxWidth
	 * @param maxHeight
	 * @param maxImpressions
	 * @param MaintenanceValue
	 */
	void addPrinter(String name, String location, int maxWidth, int maxHeight, int maxImpressions, int MaintenanceValue);

	/**
	 *Remove a printer from the system
	 *The printer name must be provided
	 *@param name
	 */
	void removePrinter(String name);

	/**
	 * Adiciona um álbum à fila de espera de uma impressora.
	 *Se efectuado com suceeso o estado do album passa para final.
	 *Para ser efectuado com sucesso o album e a impressora tem que existir, assim como a impressora tem que ser capaz de imprimir o album na totalidade e ter tamanho
	 *sufeciente para imprimir todas as fotogradias.
	 */
	void addAlbumToPrinterWaitList(String albumCode, String printerName);

	/**
	 * Impressão dos álbuns contidos na fila de espera da impressora. O comando só pode ser executado caso a impressora exista no sistema.
	 *Caso não tenha nenhum album em lista de espera será lançado o erro adequado.
	 *Os albuns e as fotografias devem ser impressas pela ordem que foram inseridas.
	 * 
	 * @param name
	 * @return De um iterador de albums
	 */
	Iterator<Album> printWaitList(String name);

	/**
	 * Caso existam impressoras a precisar de manutenção actualiza a o numero de impressoes possiveis das impressoras.
	 * @return True caso tenha sido efectuada manutencao a alguma impressora
	 */
	boolean doMaintenance();

	/**
	 * @return Returns and iterator of printers
	 */
	Iterator<Entry<String, Printer>> listPrinters ();
	
	/**
	 * Returns a printer location
	 * The printer name must be provided
	 * @param name
	 * @return Returns a string with the printer location
	 */
	String printerLocatition(String name);
	
	/**
	 * Cleans the printer waiting list
	 * The name of the printer must be provided
	 * @param name
	 */
	void cleanWaitList(String name);

}
