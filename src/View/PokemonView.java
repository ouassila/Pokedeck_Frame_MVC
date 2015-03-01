package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.PokemonControleur;
import Model.PokeListener;


public abstract class PokemonView implements PokeListener{	
	
	private static final long serialVersionUID = 1L;
	private PokemonControleur controleur;
	protected JFrame frame = new JFrame();
	
	public PokemonView(PokemonControleur controleur){	
		this.controleur = controleur;
	}
	
	public PokemonControleur getControleur(){
		return controleur;
	}
	
	public abstract void display();	
	public abstract void close();
	
	public JFrame getFrame(){
		return frame;
	}
	public JButton addButton(JPanel panel, String name){
		
		 JButton button = new JButton(name);		
		 button.setSize(20, 10);		
		 panel.add(button);
		 
		 return button;
	}
	
	public JLabel addLabel(JPanel panel, String title){
		JLabel label = new JLabel();
		label.setText(title);		
		panel.add(label);
		return label;		
	}	
	
	public JTextField addInput(JPanel panel){
		 
		JTextField input = new JTextField(20);
		input.setFont(new Font("Arial", Font.ITALIC, 14));
		input.setForeground(Color.blue);		
		panel.add(input);
		
		return input;
	}	
	
	public JFrame addWindow(String name){	
		
		JFrame frame = new JFrame(name);
		frame.setSize(800, 600);	
		frame.setLocationRelativeTo(null);
		
		return frame;		
	}	
	
	public JComboBox<String> addComboTypeCard(JPanel panel){
		
		JComboBox<String> combo = new JComboBox<String>();
		String[] types = {"<html><i>No type card selected</i></html>", "Energy", "Trainer", "Pokemon"};		
		combo = new JComboBox<String>(types);	
		panel.add(combo);
		combo.setMaximumSize(new Dimension (250, 30));			
		return combo;
	}
}
