package printSystem;

import java.io.Serializable;
import dataStructures.Iterator;

public interface Printer extends Serializable {
	
	
	//createPrinter(String name, String location, int maxWidth, int maxHeight, int maxImpressions, int MaintenanceValue)
	//Sera necessario um metodo privado para decrementar o numero maximo de impressoes
	
	//devolve nome da impressora
	String getName();
	
	//verifica se a fila de espera esta vazia
	boolean isEmpty();
	
	//devolve a altura maxima em que a impressao e' possivel
	int getMaxHeight();
	
	//devolve a largura maxima em que a impressao e' possivel
	int getMaxWidth();
	
	//devolve localizacao da impressora
	 String getLocation();
	
	//devolve o numero maximo de folhas que pode imprimir com o 
	//cartuxo cheio
	int maxImpressions();

	//devolve o numero de folhas que ainda pode imprimir
	int MaintenanceValue();

	//Actualiza o valor maximo de impressões
	boolean maintenance();
	
	//Adiciona um albuem à lista de espera da impressora
	boolean addAlbumToWaitList(Album album);
	
	//devolve o nr de impressoes restantes
	int getRest();
	//devolve iterador de albuns na lista de espera
	Iterator<Album> allAlbuns();
	
	void cleanList();
	
	 void print();
	 
	 int getPrint();
}


