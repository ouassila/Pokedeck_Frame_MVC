package Control;

import Model.Card;
import Model.PokedeckModel;
import View.PokemonView;
/**
 * Définie l'intermédiaire entre les vue et les modifications entrainées dans les classes métiers
 * @author Ouassila
 *
 */
public class PokemonControleur {
	
	public PokemonView view;
	private PokedeckModel model;
	
	public void setModel(PokedeckModel model){
		this.model = model;
	}
	
	public void displayView(){
		this.view.display();
	}
	
	public void disposeView(){
		this.view.getFrame().dispose();
	}
	
	public void notifyremoveCard(Card card) {
		this.model.removeCard(card);
	}

	public void notifyAddCard(Card card) {
		this.model.addCard(card);
	}

	public void notifyUpdateEnergy(int index, String text) {
		this.model.UpdateEnergy(index, text);
	}

	public void notifyUpdateTrainerCard(int index, String text, String text2,
			String text3, String text4, String text5) {
		this.model.UpdateTrainer(index, text, text2, text3, text4, text5);		
	}

	public void notifyUpdatePokemon(int index, String text, String text2,
			String text3, String text4, String text5, String text6) {
		this.model.UpdatePokemon(index, text, text2, text3, text4, text5, text6);		
	}

	public void setView(PokemonView view) {
		this.view = view;		
	}
}
