package printSystem;

import java.io.Serializable;
import dataStructures.Entry;
import dataStructures.Iterator;

public interface PrintManager extends Serializable {

	/**
	 * Add a photo to the system
	 * @param code
	 * @param fileName
	 * @param width
	 * @param height
	 */
	void addPhoto(String code, String fileName, int width, int height);
	/**
	 * Add an empty album to the system
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
	 * Adiciona uma impressora ao sistema dado a seu nome "name", localiza��o "location", altura "maxWidth" e lagura "maxHeight" maxima suportada,
	 *numero maximo de impressoes "maxImpressions" e valor minino aceite antes de ser efectuada uma manuten��o "MaintenanceValue".
	 *Se o valor "MaintenanceValue" for superior ao "maxImpressions" a opera��o falha.
	 *Cada impressora poder� conter uma fila de espera, contendo todos os �lbuns (em estado final) que ainda n�o foram impressos. 
	 */
	void addPrinter(String name, String location, int maxWidth, int maxHeight, int maxImpressions, int MaintenanceValue);

	/**
	 *Remove a impressora com o nome "name" do sistema.
	 *Para a impressora ser removida com successo a impressora tem que existir.
	 */
	void removePrinter(String name);

	/**
	 * Adiciona um �lbum � fila de espera de uma impressora.
	 *Se efectuado com suceeso o estado do album passa para final.
	 *Para ser efectuado com sucesso o album e a impressora tem que existir, assim como a impressora tem que ser capaz de imprimir o album na totalidade e ter tamanho
	 *sufeciente para imprimir todas as fotogradias.
	 */
	void addAlbumToPrinterWaitList(String albumCode, String printerName);

	/**
	 * Impress�o dos �lbuns contidos na fila de espera da impressora. O comando s� pode ser executado caso a impressora exista no sistema.
	 *Caso n�o tenha nenhum album em lista de espera ser� lan�ado o erro adequado.
	 *Os albuns e as fotografias devem ser impressas pela ordem que foram inseridas.
	 * 
	 * @param name
	 * @return De um iterador de albums
	 */
	Iterator<Album> printWaitList(String name);

	/**
	 * Caso existam impressoras a precisar de manuten��o actualiza a o numero de impressoes possiveis das impressoras.
	 * @return True caso tenha sido efectuada manutencao a alguma impressora
	 */
	boolean doMaintenance();

	/**
	 * @return Retorna um iterador de impressoras
	 */
	Iterator<Entry<String, Printer>> listPrinters ();
	
	/**
	 * 
	 * @param name
	 * @return Retorna a localizacao da impressora
	 */
	String printerLocatition(String name);
	
	/**
	 * Limpa a lista de espera de uma impressora
	 * @param name
	 */
	void cleanWaitList(String name);

}
