package Model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * S�rialise les donn�es dans un fichier texte "pokedeck.txt"
 * Enregistre la liste des cartes dans un fichier texte � la fin du programme
 * Lit les cartes enregistr�es au lancement du programme
 * @author Ouassila
 *
 */
public class DataSerialize {
	
	private FileInputStream FIS;
	private ObjectInputStream OIS;
	private FileOutputStream FOS;
	private ObjectOutputStream OOS;
	private String nameFile;
	private File file;
	
	public DataSerialize(String name){
		this.nameFile = name;
		file = new File(name);
	}
	
	/**
	 * cr�er le fichier "pokedeck.txt" s'il n'existe pas 
	 * initialise le fichier du flux d'entr�e
	 * @return
	 * @throws IOException
	 */
	public boolean createAndOpen() throws IOException{		
		
		if(!file.exists()){
			file.createNewFile();
			return true;
		}			
		FIS = new FileInputStream(this.nameFile);	
		return false;
	}
	
	/**
	 * initialise l'objet du flux d'entr�e si le fichier "pokedeck.txt" n'est pas vide
	 * @return true si "pokedeck.txt" n'est pas vide et OIS est initialis�e
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public boolean readFile() throws IOException, ClassNotFoundException{
		
		if(FIS.available() != 0){
			OIS = new ObjectInputStream(FIS);
			return true;
		}
		return false;						
	}
	
	/**
	 * lis le pokedeck stock� dans "pokdeck.txt"
	 * @return le pokedeck lu
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Pokedeck readPokedeck () throws ClassNotFoundException, IOException{
		return (PokedeckModel) OIS.readObject();
	}
	
	/**
	 * �crit dans le fichier "pokedeck.txt" 
	 * initialise et ferme les flux de sorties
	 * @param pokedeck
	 * @throws IOException
	 */
	public void writeFile(Pokedeck pokedeck) throws IOException{			
		FOS = new FileOutputStream(this.nameFile);
		OOS= new ObjectOutputStream(FOS);
		OOS.writeObject(pokedeck);
		OOS.flush();
		OOS.close();
	}
	
	/**
	 * ferme les flux d'entr�es
	 * @throws IOException
	 */
	public void closeIS() throws IOException{
		FIS.close();
		OIS.close();
	}
}
