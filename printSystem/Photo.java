package printSystem;

import java.io.Serializable;

public interface Photo extends Serializable{
	
	//Cria uma fotografia com o codigo "code" largura "width" e altura "height" especificados.
	//createPhoto(int code, int width, int heith);
	
	//Devolve o codigo da fotogradia.
	String getCode();
	
	//Devolde a altura da fotogradia.
	int getWidth();
	
	//Devolde a largura da fotogradia.
	int getheight();
	
	//Devolde o nome do ficheiro onde se encontrao guardados os dados
	String savedFile();
}
