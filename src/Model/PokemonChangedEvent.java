package Model;

import java.util.ArrayList;
import java.util.EventObject;
/**
 * Redefinie une nouvelle pile de cartes à chaque action sur les cartes
 * @author Ouassila
 *
 */

public class PokemonChangedEvent extends EventObject {
	
	private ArrayList<Card>  list;
	
	public PokemonChangedEvent(Object source, ArrayList<Card> deck) {
		super(source);
		this.list = deck;
	}
	
	public ArrayList<Card> getListCards(){
		return this.list;
	}
}
