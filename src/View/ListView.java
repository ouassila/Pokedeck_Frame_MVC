package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Control.PokemonControleur;
import Model.Card;
import Model.PokemonChangedEvent;
/**
 * Affichage de la liste des cartes enregistrées dans le pokedeck
 * @author Ouassila
 *
 */
public class ListView extends PokemonView{
	
	private static final long serialVersionUID = 1L;
	private int current_index = 0;
	private ArrayList<Card> deck;
	private JLabel label_name = new JLabel();
	private JLabel label_type = new JLabel();
	private JLabel label_properties = new JLabel();
	private JButton btn_prev = new JButton("Previous");
	private JButton btn_next = new JButton("Next");
	
	public ListView(PokemonControleur controleur, ArrayList<Card> deck) {
		super(controleur);
		this.deck = deck;
		frame = addWindow("List of cards");
		view_start();
	}

	@Override
	public void pokemonChanged(PokemonChangedEvent event) {
		
		this.setList(event.getListCards());
		btn_prev.setEnabled(false);
	    btn_next.setEnabled(true);
	    current_index = 0;
		if(this.deck.size()>0 && current_index>-1 && current_index<this.deck.size()){
			label_name.setText(this.deck.get(current_index).getName());
			label_type.setText(this.deck.get(current_index).getClass().getCanonicalName());
			label_properties.setText(this.deck.get(current_index).toString());
		}
		else {
			label_name.setText("");
			label_type.setText("");
			label_properties.setText("");
		}
	}
	
	public void setList(ArrayList<Card> deck){
		this.deck=deck;
	}
	
	public void view_start() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout( 0, 2, 15, 15));
		
		JPanel panel_buttons = new JPanel();		  
		panel_buttons.add(btn_prev);			  
	    panel_buttons.add(btn_next);
		
		label_name = new JLabel();
		label_type = new JLabel();
		label_properties = new JLabel();
		current_index = 0;
		
		if (deck.size() > 0){
			  
			  label_name.setText(deck.get(current_index).getName());
			  label_type.setText(deck.get(current_index).getClass().getCanonicalName());
			  label_properties.setText(deck.get(current_index).toString());
		}
		else{
		//	btn_next.setEnabled(false);
		}
		
		JScrollPane pCard = new JScrollPane(label_properties, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
 
		    
	    panel.add(new JLabel("Name :"));
	    panel.add(label_name);
	    panel.add(new JLabel("Type :"));
	    panel.add(label_type);
	    panel.add(new JLabel("Properties :"));
	   
	    btn_prev.setEnabled(false);
	    panel.add(pCard);
	    panel.add(btn_prev);
	    panel.add(btn_next);
	    
	    btn_prev.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (current_index-1 > -1){
					current_index = current_index-1;
					ListView.this.label_name.setText(deck.get(current_index).getName());
					ListView.this.label_type.setText(deck.get(current_index).getClass().toString());
					ListView.this.label_properties.setText(deck.get(current_index).toString());
					btn_next.setEnabled(true);
				}
				else{
					btn_prev.setEnabled(false);
					btn_next.setEnabled(true);
				}				
			}	      	
	     });	      
	     btn_next.addActionListener(new ActionListener(){
			 @Override
			 public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (current_index+1 < deck.size()){
					current_index =current_index+1;
					ListView.this.label_name.setText(deck.get(current_index).getName());
					ListView.this.label_type.setText(deck.get(current_index).getClass().getCanonicalName());
					ListView.this.label_properties.setText(deck.get(current_index).toString());
					btn_prev.setEnabled(true);
				}
				else{
					btn_next.setEnabled(false);
					btn_prev.setEnabled(true);
				}
			 }	    	
	      });
			 
	    frame.add(panel, BorderLayout.CENTER);
		panel_buttons.setPreferredSize(new Dimension(10, 10));
		frame.add(panel_buttons, BorderLayout.SOUTH);	
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
