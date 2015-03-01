package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.PokemonControleur;
import Model.Card;
import Model.EnergyCard;
import Model.Pokedeck;
import Model.Pokemon;
import Model.PokemonChangedEvent;
import Model.TrainerCard;
/**
 * Affichage du module d'édition de cartes
 * @author Ouassila
 *
 */
public class EditView extends PokemonView{
	
	private Pokedeck pokedeck_edit;
	
	public EditView(PokemonControleur controleur, ArrayList<Card> deck) {
		super(controleur);
		pokedeck_edit = new Pokedeck();
		pokedeck_edit.setDeck(deck);
		frame = addWindow("Pokedeck : Edit card");
		this.edit_start();
	}

	@Override
	public void pokemonChanged(PokemonChangedEvent event) {
		pokedeck_edit.setDeck(event.getListCards());		
	}
	
	public void edit_start() {
		
		JPanel panel = new JPanel();
		
		JLabel label_name= this.addLabel(panel, "Name : ");	
		final JTextField input_name =  this.addInput(panel); 		
		
		panel.add(label_name);		
		panel.add(input_name);
		
		final JButton edit = this.addButton(panel, "Edit");	
		
		edit.addActionListener (new ActionListener () {			
			public void actionPerformed(ActionEvent e) {
				
				JPanel panel_properties = new JPanel();
				int index = pokedeck_edit.getIndexCardByName(input_name.getText().trim());
				
				if(index != -1){
			    	edit.setVisible(false);
			    	//griser l'input et le bloquer
			    	input_name.setEditable(false);
			    	input_name.setForeground(new Color(128,128,128));
			    	
			    	String type = pokedeck_edit.listOfCards().get(index).getClass().getCanonicalName();
			    	panel_properties.setVisible(false);
			    	
					if(type == "Pokemon"){		        		
						panel_properties = addPanelEditPokemonCard(index);			        	
			        }
			        else if(type == "TrainerCard"){	
			        	panel_properties = addPanelEditTrainerCard(index);	  
			        }
			        else{
			        	panel_properties = addPanelEditEnergyCard(index);	  
			        	
			        }					
					panel_properties.setVisible(true);
					frame.add( panel_properties , BorderLayout.CENTER);
					frame.setVisible(true);				    	
				}
				else{
					JOptionPane.showMessageDialog(frame, "Card not found", 
					      "Error", JOptionPane.ERROR_MESSAGE);				
				}				
			}		     
		});
		
		frame.setLayout(new BorderLayout()) ;
	    panel.setBorder(new EmptyBorder(60, 10, 0, 10));
	    frame.add( panel , BorderLayout.NORTH);	
	}

	private JPanel addPanelEditEnergyCard(final int index) {
		
		EnergyCard energyCard = (EnergyCard) pokedeck_edit.listOfCards().get(index);
		JPanel panel = new JPanel();
		JPanel panel_button = new JPanel(); 
		JButton edit = this.addButton(panel_button, "Edit") ;
		
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.setLayout(new GridLayout (0, 2, 15, 15));
		
		panel.add(new JLabel("Energy Card :"));
		panel.add(new JLabel(energyCard.toString()));
		
		panel.add(new JLabel("Name :"));
		final JTextField input_name = this.addInput(panel); 
		
		edit.addActionListener(new ActionListener() {				
	        public void actionPerformed(ActionEvent e) {
	        	EditView.this.getControleur().notifyUpdateEnergy(index, input_name.getText());
	        //	energyCard.updateCard(0, input_name.getText());	    				
				EditView.this.close();
	        }
	    });
		
		panel_button.add(edit);		
		frame.add (panel_button , BorderLayout.SOUTH);	
		
		return panel;
	}	
	
	public JPanel addPanelEditTrainerCard(final int index ) {
		
		TrainerCard trainer = (TrainerCard) pokedeck_edit.listOfCards().get(index);
		JPanel panel = new JPanel();
		JPanel panel_button = new JPanel(); 
		JButton edit = this.addButton(panel_button, "Edit") ;
		
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.setLayout(new GridLayout (0, 2, 15, 15));
		
		panel.add(new JLabel("Trainer Card :"));
		panel.add(new JLabel(trainer.toString()));
		
		panel.add(new JLabel("Name :"));
		final JTextField input_name = this.addInput(panel); 
		
		panel.add(new JLabel("Description :"));
		final JTextField input_desc = this.addInput(panel); 
		
		panel.add(new JLabel("Type :"));
		final JTextField input_type = this.addInput(panel); 
		
		
		panel.add(new JLabel("Rule :"));
		final JTextField input_rule = this.addInput(panel); 
		
		panel.add(new JLabel("Number :"));
		final JTextField input_number = this.addInput(panel); 		
		
		
		edit.addActionListener(new ActionListener() {				
	        public void actionPerformed(ActionEvent e) {
	        	EditView.this.getControleur().notifyUpdateTrainerCard(index, input_desc.getText(), input_name.getText(), input_rule.getText(), input_type.getText(), input_number.getText());
	        	close();
	        }
	    });		
		panel_button.add(edit);
		frame.add ( panel_button , BorderLayout.SOUTH);	
		
		return panel;
	}
	
	
	public JPanel addPanelEditPokemonCard(final int index ) {
		
		Pokemon pokemon = (Pokemon) pokedeck_edit.listOfCards().get(index);
		JPanel panel = new JPanel();
		JPanel panel_button = new JPanel();
		
		JButton edit = this.addButton(panel_button, "Edit") ; 		
		
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.setLayout(new GridLayout (0, 2, 15, 15));
		
		panel.add(new JLabel("Pokemon Card :"));		
		panel.add(new JLabel(pokemon.toString()));	
		
		panel.add(new JLabel("Name :"));
		final JTextField input_name = this.addInput(panel); 
		
		panel.add( new JLabel("Description :"));
		final JTextField input_desc = this.addInput(panel); 
		
		panel.add( new JLabel("Type :"));
		final JTextField input_type = this.addInput(panel); 
		
	
		panel.add(new JLabel("Stage :"));
		final JTextField input_stage = this.addInput(panel); 
		
		panel.add(new JLabel("HP :"));
		final JTextField input_hp = this.addInput(panel); 
		
		panel.add(new JLabel("Number :"));
		final JTextField input_number = this.addInput(panel); 		
		
		edit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	EditView.this.getControleur().notifyUpdatePokemon(index, input_type.getText(),  input_hp.getText(), input_stage.getText(), input_number.getText(), input_desc.getText(), input_name.getText() );
	        	close();
	        }
	    });
		
		panel_button.add(edit);		
		frame.add (panel_button , BorderLayout.CENTER);	
		
		return panel;
	}

	@Override
	public void display() {
		frame.setVisible(true);
	}

	@Override
	public void close() {
		frame.dispose();
	}
}
