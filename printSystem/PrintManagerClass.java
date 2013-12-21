package printSystem;

import dataStructures.BinarySearchTree;
import dataStructures.Dictionary;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;
import dataStructures.SepChainHashTable;
import exceptions.*;

public class PrintManagerClass implements PrintManager {

	static final long serialVersionUID = 0L;

	private OrderedDictionary<String,Printer>printers;
	private Dictionary<String,Album> albuns;
	private Dictionary<String,Photo> photos;

	public PrintManagerClass (){
		this.printers = new BinarySearchTree<String,Printer>();
		this.albuns = new SepChainHashTable<String,Album>(2000);
		this.photos = new SepChainHashTable<String,Photo>(15000);
	}

	@Override
	public void addPhoto(String code, String fileName, int width, int height) throws PhotoAlreadyExistException {
		if(existPhoto(code) != null) {
			throw new PhotoAlreadyExistException();
		}
		else {
			photos.insert(code,new PhotoClass(code ,width, height,fileName));
		}
	}

	@Override
	public void addAlbum(String code) throws AlbumAlreadyExistException {
		if(existAlbum(code) != null)
			throw new AlbumAlreadyExistException();
		else {
			albuns.insert(code,new AlbumClass(code));
		}
	}

	@Override
	public void addPhotoToAlbum(String code, String albumCode, int width, int height) throws PhotoNotInTheSystemException, AlbumNotInTheSystemException, PhotoOverSizeException   {
		Photo photo = existPhoto(code);

		if(photo == null)
			throw new PhotoNotInTheSystemException();
		else if(existAlbum(albumCode) == null)
			throw new AlbumNotInTheSystemException();
		else if(photo.getheight()< height || photo.getWidth() < width) 
			throw new PhotoOverSizeException();
		else{
			albuns.find(albumCode).addPhoto(photo, width, height);
		}
	}

	@Override
	public void addPrinter(String name, String location, int maxWidth, int maxHeight, int maxImpressions, int maintenanceValue) throws PrinterMaintenaceErrorException, PrinterAlreadyExistException  {
		Printer printer = existPrinter(name);

		if(maxImpressions>maintenanceValue){
			if(printer != null)
				throw new PrinterAlreadyExistException();
			else {
				printers.insert(name, new PrinterClass(name,location,maxHeight,maxWidth,maxImpressions,maintenanceValue));
			}
		}
		else
			throw new PrinterMaintenaceErrorException();
	}

	@Override
	public void removePrinter(String name) throws PrinterNotInTheSystemException {
		Printer printer = existPrinter(name);

		if(printer!=null) {
			printers.remove(name);	
		}
		else
			throw new PrinterNotInTheSystemException();

	}

	@Override
	public void addAlbumToPrinterWaitList(String albumCode, String printerName) throws AlbumNotInTheSystemException, PrinterNotInTheSystemException, PrinterNeedMaintenanceException, AlbumToBigException, AlbumIsEmptyException{
		Album album = existAlbum(albumCode); 
		Printer printer = existPrinter(printerName);

		if(album == null)
			throw new AlbumNotInTheSystemException();
		else if(printer == null) 
			throw new PrinterNotInTheSystemException();
		else if(album.totalFotos() > printer.getRest())
			throw new PrinterNeedMaintenanceException();
		else if(album.alturaFoto() > printer.getMaxHeight() || album.larguraFoto() > printer.getMaxWidth())
			throw new AlbumToBigException();
		else if(album.totalFotos()== 0)
			throw new AlbumIsEmptyException();
		else {
			printer.addAlbumToWaitList(album);
			albuns.remove(albumCode);
		}
	}	

	@Override
	public Iterator<Album> printWaitList(String name) throws PrinterNotInTheSystemException, PrinterEmptyWaitListException {
		Printer printer = existPrinter(name); 

		if(printer!=null) {
			if(isEmpty(printer))
				throw new PrinterEmptyWaitListException();
			else {
				printer.print();
				return printer.allAlbuns();	
			}
		}
		else
			throw new PrinterNotInTheSystemException();
	}
	
	@Override
	public Iterator<Entry<String,Printer>> listPrinters() {
		return printers.iterator();
	}

	@Override
	public boolean doMaintenance() {
		Iterator<Entry<String, Printer>> printer = printers.iterator();
		boolean maintenanceCheck = false;
		while(printer.hasNext()){
			if(printer.next().getValue().maintenance())
				maintenanceCheck = true;
		}
		return maintenanceCheck;


	}
	
	@Override
	public void cleanWaitList(String name){
		existPrinter(name).cleanList();
	}

	@Override
	public String printerLocatition(String name) {
		return existPrinter(name).getLocation();
	}
	
	private boolean isEmpty(Printer printer){
		return printer.isEmpty();
	}

	private Album existAlbum(String code){
		Album album = albuns.find(code);
		if(album != null)
			return album;
		else
			return null;

	}

	private Printer existPrinter(String name){
		Printer printer = printers.find(name);
		if(printer !=null)
			return printer;
		else
			return null;
	}

	private Photo existPhoto(String code){
		Photo photo = photos.find(code);
		if(photo != null)
			return photo;
		else
			return null;

	}
}
