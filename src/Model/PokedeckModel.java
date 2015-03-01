package Model;
import javax.swing.event.EventListenerList;
/**
 * Gestion des listeners
 * Notifie toutes actions (création, suppression et edition de cartes) aux listeners
 * @author Ouassila
 *
 */
public class PokedeckModel extends Pokedeck{

	private EventListenerList pokelist;
	
	public PokedeckModel(){
		super();
		this.pokelist= new EventListenerList();
	}
	
	public void addCard(Card c) {
		super.addCard(c);		
		this.firePokedeckChanged();
	}	
	
	private void firePokedeckChanged() {
		PokeListener[] pokemonlist = (PokeListener[]) this.pokelist.getListeners(PokeListener.class);
		
		for(PokeListener listener : pokemonlist){
			listener.pokemonChanged(new PokemonChangedEvent(this,this.listOfCards()));
		}
	}

	public void removeCard(Card c){
		super.removeCard(c);
		this.firePokedeckChanged();
	}	
	
	public void addPokeListener(PokeListener l){
		this.pokelist.add(PokeListener.class, l);
	
	}
	
	public void removePokeListener(PokeListener l){
		this.pokelist.remove(PokeListener.class, l);
	}

	public void UpdateEnergy(int index, String text) {
		this.listOfCards().get(index).updateCard(0, text);
		this.firePokedeckChanged();
	}
	
	public void UpdateTrainer(int index, String text, String text2, String text3, String text4, String text5) {
		this.listOfCards().get(index).updateCard(1,text);
		this.listOfCards().get(index).updateCard(2,text2);
		this.listOfCards().get(index).updateCard(3,text3);
		this.listOfCards().get(index).updateCard(4,text4);
		this.listOfCards().get(index).updateCard(5,text5);
		this.firePokedeckChanged();
	}

	public void UpdatePokemon(int index, String text, String text2, String text3, String text4, String text5, String text6) {
		this.listOfCards().get(index).updateCard(1,text);
		this.listOfCards().get(index).updateCard(2,text2);
		this.listOfCards().get(index).updateCard(3,text3);
		this.listOfCards().get(index).updateCard(4,text4);
		this.listOfCards().get(index).updateCard(5,text5);
		this.listOfCards().get(index).updateCard(6,text6);
		this.firePokedeckChanged();
		
	}
}
