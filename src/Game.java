import java.io.IOException;
import Control.PokemonControleur;
import Model.*;
import View.*;
/**
 * Point d'entrée du programme
 * Pour chaque module , création d'une vue, d'un controleur et d'un model
 * @author Ouassila
 *
 */
public class Game {
	private static DataSerialize datas = new DataSerialize("pokedeck.txt");
	private static PokedeckModel pokedeck;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {	
		
		pokedeck=new PokedeckModel();
		globalMenuWithDataSerialize();
		
		PokemonControleur control_list = new PokemonControleur();
		PokemonView view_list = new ListView (control_list, pokedeck.listOfCards());		
		pokedeck.addPokeListener(view_list);		
		control_list.setModel(pokedeck);		
		control_list.setView(view_list);
		
		PokemonControleur control_edit = new PokemonControleur();	
		PokemonView view_edit = new EditView(control_edit, pokedeck.listOfCards());
		pokedeck.addPokeListener(view_edit);		
		control_edit.setModel(pokedeck);		
		control_edit.setView(view_edit);		
		
		PokemonControleur control_create = new PokemonControleur();
		PokemonView view_create = new CreateView(control_create);
		pokedeck.addPokeListener(view_create);		
		control_create.setModel(pokedeck);		
		control_create.setView(view_create);
		
		PokemonControleur control_remove = new PokemonControleur();
		PokemonView view_remove = new DeleteView(control_remove, pokedeck.listOfCards());
		pokedeck.addPokeListener(view_remove);		
		control_remove.setModel(pokedeck);		
		control_remove.setView(view_remove);		
		
		PokemonControleur control_main = new PokemonControleur();		
		PokemonView view_main = new MenuFrame(control_main, view_list, view_remove, view_create, view_edit);		
		pokedeck.addPokeListener(view_main);		
		control_main.setModel(pokedeck);		
		control_main.setView(view_main);
	}
	
	public static  void globalMenuWithDataSerialize() throws IOException, ClassNotFoundException{
	
		if(!datas.createAndOpen()){
			if(datas.readFile()){				
				pokedeck = (PokedeckModel) datas.readPokedeck();
				datas.closeIS();
			}
			else{
				System.out.println("Aucun pokedeck n'est stocké.");
			}
		}
		else {
			System.out.println("Un fichier d'enregistrement de pokedeck a été créé.");
		}
	}
}
