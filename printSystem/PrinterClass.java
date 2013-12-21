package printSystem;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;

public class PrinterClass implements Printer {

	static final long serialVersionUID = 0L;
	
	private String name,localizacao;
	private int maxHeight, maxWidth, maxImpr, valorManut, impreRest, print;
	private List<Album> albuns;

	
	public PrinterClass(String name, String localizacao, int maxHeight,int maxWidth, int maxImpr, int valorManut) {

		this.name = name;
		this.localizacao = localizacao;
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
		this.maxImpr = maxImpr;
		this.valorManut = valorManut;
		this.impreRest = maxImpr;
		this.print = maxImpr;
		cleanList();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public boolean isEmpty(){
		return albuns.isEmpty();
		
	}

	@Override
	public int getMaxHeight() {
		// TODO Auto-generated method stub
		return maxHeight;
	}
	@Override
	public String getLocation(){
		// TODO Auto-generated method sub
		return localizacao;
	}

	@Override
	public int getMaxWidth() {
		// TODO Auto-generated method stub
		return maxWidth;
	}

	@Override
	public int maxImpressions() {
		// TODO Auto-generated method stub
		return maxImpr;
	}

	@Override
	public int MaintenanceValue() {
		// TODO Auto-generated method stub
		return valorManut;
	}

	@Override
	public boolean maintenance() {
		// TODO Auto-generated method stub
		boolean result=false;
		if(print<valorManut){
			print = maxImpr;
			impreRest=maxImpr;
			result=true;
		}
		return result;
	}

	@Override
	public boolean addAlbumToWaitList(Album album) {
		if(album.totalFotos()<= impreRest){
			albuns.addLast(album);
			impreRest-=album.totalFotos();
			return true;
			}
		return false;
		}
	
	public void print() {
		print = impreRest;
	}
	@Override
	public int getRest(){
		return impreRest;
	}
	
	public int getPrint(){
		return print;
	}
	
	@Override
	public Iterator<Album> allAlbuns(){
		return albuns.iterator();
		
	}
	
	public void cleanList() {
		this.albuns = new DoublyLinkedList<Album>();
	}
}


