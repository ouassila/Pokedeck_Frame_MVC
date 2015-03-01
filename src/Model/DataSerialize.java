package Model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Sérialise les données dans un fichier texte "pokedeck.txt"
 * Enregistre la liste des cartes dans un fichier texte à la fin du programme
 * Lit les cartes enregistrées au lancement du programme
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
	 * créer le fichier "pokedeck.txt" s'il n'existe pas 
	 * initialise le fichier du flux d'entrée
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
	 * initialise l'objet du flux d'entrée si le fichier "pokedeck.txt" n'est pas vide
	 * @return true si "pokedeck.txt" n'est pas vide et OIS est initialisée
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
	 * lis le pokedeck stocké dans "pokdeck.txt"
	 * @return le pokedeck lu
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Pokedeck readPokedeck () throws ClassNotFoundException, IOException{
		return (PokedeckModel) OIS.readObject();
	}
	
	/**
	 * écrit dans le fichier "pokedeck.txt" 
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
	 * ferme les flux d'entrées
	 * @throws IOException
	 */
	public void closeIS() throws IOException{
		FIS.close();
		OIS.close();
	}
}
