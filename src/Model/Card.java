package Model;

import java.io.Serializable;
/**
 * Représente l'ensemble des cartes du pokedeck
 * @author Ouassila
 *
 */
public abstract class Card implements Serializable{
	
	protected String name;
	static protected final long serialVersionUID = 10L;
	
	public Card(String name){
		this.name = name;	
	}
	
	public String getName(){
		return name;
	}
	
	public abstract String toString();	
	public abstract void updateCard(int indexLabel, String newValue);	
	public abstract void accept(CardVisitor v);	
}
