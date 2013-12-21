package printSystem;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;


public class AlbumClass implements Album {


	static final long serialVersionUID = 0L;

	private String code;
	private int width, height;
	private List<Photo> photos;


	public AlbumClass(String code) {
		this.code = code;
		photos = new DoublyLinkedList<Photo>();
	}

	@Override
	public int totalFotos(){
		return photos.size();
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void addPhoto(Photo p, int width, int height) {
			photos.addLast(new PhotoClass(p.getCode(), width, height, p.savedFile()));
			if(this.width < width)
				this.width = width;
			if(this.height < height)
				this.height = height;
	}

	@Override
	public void removePhoto(Photo photo) {
		photos.remove(photo);
	}

	@Override
	public int alturaFoto(){
		return height;
	}

	@Override
	public int larguraFoto() {
		return width;
	}

	@Override
	public Iterator<Photo> photos(){
		return photos.iterator();
	}



}
