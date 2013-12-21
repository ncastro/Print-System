import java.util.Scanner;
import dataStructures.Entry;
import dataStructures.Iterator;
import exceptions.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import printSystem.*;

public class Main {

	static final long serialVersionUID = 0L;

	public static final String I_FOTO = "if";
	public static final String I_SUCE = "Insercao de fotografia com sucesso.";
	public static final String I_FALHA = "Existencia de fotografia referida.";
	public static final String I_ALBUM = "ia";
	public static final String I_A_SUCE = "Insercao de album com sucesso.";
	public static final String I_A_FALHA = "Existencia de album referido.";
	public static final String ADD_FOTO = "af";
	public static final String ADD_SUC = "Adicao de fotografia a album.";
	public static final String FOTO_INEX = "Inexistencia de fotografia referida.";
	public static final String ALBUM_INEX = "Album referido invalido.";
	public static final String IMPRESS_IMP = "Impossibilidade de realizar impressao.";
	public static final String I_IMPRESS = "ii";
	public static final String I_I_SUCE = "Insercao de impressora com sucesso.";
	public static final String ERRO_MANUT = "Valor de manutencao nao inferior a maximo de impressoes.";
	public static final String I_I_FALHA = "Existencia de impressora referida.";
	public static final String REM_I = "ri";
	public static final String REM_SUC = "Remocao de impressora com sucesso.";
	public static final String I_INEX = "Inexistencia de impressora referida.";
	public static final String I_INEXI = "Inexistencia de impressoras.";
	public static final String ADD_A_FILA = "ap";
	public static final String ADD_A_SUCE="Adicao de album a impressora com sucesso.";
	public static final String INEX_ALB ="Inexistencia de album referido.";
	public static final String NEED_MAN="Necessidade de manutencao antes de adicao do album.";
	public static final String A_VAZIO= "Album vazio.";
	public static final String I_ESPERA="ei";
	public static final String FILA_V="Fila de espera vazia.";
	public static final String MANU="em";
	public static final String MANU_EX="Manutencao executada.";
	public static final String INEX_IMP_M="Inexistencia de impressoras a necessitar de manutencao.";
	public static final String LISTA_I="li";

	public static void main(String[] args) {

		PrintManager system = load();
		Scanner in = new Scanner(System.in);
		String comm;
		while(in.hasNext()){
			comm = in.next().toLowerCase();

			if(comm.equalsIgnoreCase(I_FOTO)){
				insertPhoto(in, system);
			}
			else if(comm.equalsIgnoreCase(I_ALBUM)){
				insertAlbum(in, system);
			}
			else if(comm.equalsIgnoreCase(ADD_FOTO)){
				addPhotoAlbum(in, system);
			}
			else if(comm.equalsIgnoreCase(I_IMPRESS)){
				insertPrinter(in,system);
			} 
			else if(comm.equalsIgnoreCase(REM_I)){
				removePrinter(in, system);
			}
			else if(comm.equalsIgnoreCase(ADD_A_FILA)){
				addAlbumToPrinter(in, system);
			}
			else if(comm.equalsIgnoreCase(I_ESPERA)){
				printPrinterWaitList(in, system);
			}
			else if(comm.equalsIgnoreCase(LISTA_I)){
				listPrinters(in, system);
			}
			else if(comm.equalsIgnoreCase(MANU)){
				doMaintenance(in, system);
			}
			System.out.println();
		}

		save(system);
	}

	private static PrintManager load() {
		PrintManager x = null;
		try{
			ObjectInputStream file = new ObjectInputStream(new FileInputStream("data"));
			x = (PrintManagerClass) file.readObject();
			file.close();
		}
		catch(IOException e){
			x = new PrintManagerClass();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			System.exit(0);
		}
		return x;
	}

	private static void save(PrintManager system) {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data"));
			out.writeObject(system);
			out.flush();
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	private static void insertPhoto(Scanner in, PrintManager system){
		String code = in.next();
		String file = in.nextLine().toLowerCase();
		file = file.substring(1);
		int maxWidth = in.nextInt();
		int maxHeight = in.nextInt();

		try{
			system.addPhoto(code, file, maxWidth, maxHeight);
			System.out.println(I_SUCE);
		}
		catch (PhotoAlreadyExistException e){
			System.out.println(I_FALHA);
		}
	}

	private static void insertAlbum(Scanner in, PrintManager system){
		String code = in.next().toLowerCase();
		in.nextLine();

		try{
			system.addAlbum(code);
			System.out.println(I_A_SUCE);
		}
		catch (AlbumAlreadyExistException e){
			System.out.println(I_A_FALHA);
		}
	}

	private static void addPhotoAlbum(Scanner in, PrintManager system){
		String code = in.next();
		String albumCode = in.next();
		int maxWidth = in.nextInt();
		int maxHeight = in.nextInt();

		try{
			system.addPhotoToAlbum(code, albumCode, maxWidth, maxHeight);
			System.out.println(ADD_SUC);
		}
		catch (PhotoNotInTheSystemException e) {
			System.out.println(FOTO_INEX);
		}
		catch (AlbumNotInTheSystemException e) {
			System.out.println(ALBUM_INEX);
		}
		catch (PhotoOverSizeException e){
			System.out.println(IMPRESS_IMP);
		}
	}

	private static void insertPrinter(Scanner in, PrintManager system){
		String name = in.nextLine().toLowerCase();
		name = name.substring(1);
		String location = in.nextLine().toLowerCase();
		int maxWidth = in.nextInt();
		int maxHeight = in.nextInt();
		int maxImpressions = in.nextInt();
		int MaintenanceValue = in.nextInt();

		try{
			system.addPrinter(name, location, maxWidth, maxHeight, maxImpressions, MaintenanceValue);
			System.out.println(I_I_SUCE);
		}
		catch (PrinterMaintenaceErrorException e) {
			System.out.println(ERRO_MANUT);
		}
		catch(PrinterAlreadyExistException e) {
			System.out.println(I_I_FALHA);
		}
	}

	private static void removePrinter(Scanner in, PrintManager system){
		String name = in.nextLine().toLowerCase();
		name = name.substring(1);

		try{
			system.removePrinter(name);
			System.out.println(REM_SUC);
		}
		catch (PrinterNotInTheSystemException e) {
			System.out.println(I_INEX);
		}
	}

	private static void addAlbumToPrinter(Scanner in, PrintManager system){
		String albumCode = in.next();
		String printerName =in.nextLine().toLowerCase();
		printerName = printerName.substring(1);

		try{
			system.addAlbumToPrinterWaitList(albumCode, printerName);
			System.out.println(ADD_A_SUCE);
		}
		catch(AlbumNotInTheSystemException e){
			System.out.println(INEX_ALB);
		}
		catch(PrinterNotInTheSystemException e){
			System.out.println(I_INEX);
		}
		catch(PrinterNeedMaintenanceException e){
			System.out.println(NEED_MAN);
		}
		catch(AlbumToBigException e){
			System.out.println(IMPRESS_IMP);
		}
		catch(AlbumIsEmptyException e){
			System.out.println(A_VAZIO);
		}
	}

	private static void printPrinterWaitList(Scanner in, PrintManager system){
		String printerName = in.nextLine().toLowerCase();
		printerName = printerName.substring(1);

		try{
			Iterator<Album> itr = system.printWaitList(printerName);
			System.out.println(printerName + " " + system.printerLocatition(printerName)+".");
			while(itr.hasNext()) {
				Iterator<Photo> it = itr.next().photos();
				while(it.hasNext()){
					System.out.println(it.next().savedFile() + " " + "impresso.");
				}

			}
			system.cleanWaitList(printerName);
		}
		catch(PrinterNotInTheSystemException e) {
			System.out.println(I_INEX);
		}
		catch(PrinterEmptyWaitListException e) {
			System.out.println(FILA_V);
		}
	}

	private static void listPrinters(Scanner in, PrintManager system){
		Iterator<Entry<String, Printer>> itr = system.listPrinters();
		if(itr.hasNext()){
			while(itr.hasNext()){
				Printer p = itr.next().getValue();
				if(p.getPrint()>=p.MaintenanceValue())
					System.out.println("- " + p.getName()+".");
				else
					System.out.println("- " + p.getName()+" necessita de manutencao.");
			}
		}
		else System.out.println(I_INEXI);
	}


	private static void doMaintenance(Scanner in, PrintManager system) {
		if(system.doMaintenance())
			System.out.println(MANU_EX);
		else
			System.out.println(INEX_IMP_M);
	}



}




