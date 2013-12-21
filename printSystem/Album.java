package printSystem;

import java.io.Serializable;

import dataStructures.Iterator;

public interface Album extends Serializable{
	
	//Cria um album vazio
	//createAlbum(int code)
	
	//devolve o codigo de id. do album
	String getCode();
	//devovle total de fotos do album
	int totalFotos();

	//adciona foto ao album
	void addPhoto(Photo p, int with, int heith);

	//remove foto do album
	void removePhoto(Photo photo);
	
	//devolve a maior altura duma das fotos
	int alturaFoto();
	//devolve a maior largura d euma das fotos
	int larguraFoto();
	
	//devolve o iterador de fotos
	Iterator<Photo> photos();
	
}
