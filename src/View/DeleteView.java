package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.PokemonControleur;
import Model.Card;
import Model.*;
/**
 * Affichage du module de suppression de cartes
 * @author Ouassila
 *
 */
public class DeleteView extends PokemonView{

	private Pokedeck pokedeck_delete;

	public DeleteView(PokemonControleur controleur, ArrayList<Card> deck) {
		super(controleur);
		pokedeck_delete = new Pokedeck();
		pokedeck_delete.setDeck(deck);
		frame = addWindow("Remove Card");
		this.remove_start();
	}

	@Override
	public void pokemonChanged(PokemonChangedEvent event) {
		pokedeck_delete.setDeck(event.getListCards());
	}
	
	public void remove_start(){
		
		JPanel panel = new JPanel() ;        
        panel.setPreferredSize(new Dimension(200, 200));        
        JPanel panel_buttons = new JPanel();       
		
		JLabel label_name= this.addLabel(panel, "Name : ");		
		panel.add(label_name);
		final JTextField input_name =  this.addInput(panel); 
		
		panel.add(label_name);		
		panel.add(input_name);
		
		JButton delete = this.addButton(panel_buttons, "Delete");	
		
		delete.addActionListener (new ActionListener () {			
			public void actionPerformed(ActionEvent e) {
				
				int index = pokedeck_delete.getIndexCardByName(input_name.getText().trim());
				
				if(index != -1){
			    	int confirmed = JOptionPane.showInternalConfirmDialog(frame.getContentPane(), 
			                "Are you sure you remove this card?", "Confirm Quit", 
			                JOptionPane.YES_NO_OPTION); 
			        
			        if (confirmed == JOptionPane.YES_OPTION){ 
			        	DeleteView.this.getControleur().notifyremoveCard(pokedeck_delete.listOfCards().get(index));
						DeleteView.this.close();
			        } 
				}
				else{
					JOptionPane.showMessageDialog(frame, "Card not found", 
					      "Error", JOptionPane.ERROR_MESSAGE);				
				}
			}			     
		});
		
		this.frame.add ( panel , BorderLayout.CENTER);
		this.frame.add ( panel_buttons , BorderLayout.SOUTH);	
	}

	@Override
	public void display() {
		this.frame.setVisible(true);
	}
	
	@Override
	public void close() {
		this.frame.dispose();
	}	
}
