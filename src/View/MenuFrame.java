package View;
import Control.PokemonControleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.Card;
import Model.DataSerialize;
import Model.EnergyCard;
import Model.Pokedeck;
import Model.PokedeckModel;
import Model.Pokemon;
import Model.PokemonChangedEvent;
import Model.TrainerCard;

public class MenuFrame extends PokemonView{
	
	private  DataSerialize datas = new DataSerialize("pokedeck.txt");
	private PokemonView list, remove , create, edit;	
	private PokedeckModel pokedeckModel = new PokedeckModel();	
	private int current_index = 0;
	
	public MenuFrame(PokemonControleur controleur, PokemonView view_list,PokemonView view_delete, PokemonView view_create, PokemonView view_edit) {
		super(controleur);
		this.list = view_list;
		this.remove = view_delete;
		this.edit=  view_edit;
		this.create = view_create;		
		frame  = addWindow("Menu");
		this.start();
	}
	
	public void start(){
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		JPanel panel = new JPanel();
		
		final JButton btn_create = this.addButton(panel, "Create Card");
		final JButton btn_view = this.addButton(panel, "List of cards");
		final JButton btn_edit = this.addButton(panel, "Edit Card");
		final JButton btn_remove = this.addButton(panel, "Remove Card");		
		
		frame.addWindowListener( 
			new WindowAdapter() { 
		        public void windowClosing(WindowEvent e) { 
		        	  
	            	try {
						MenuFrame.this.datas.writeFile(MenuFrame.this.pokedeckModel);
					} catch (IOException e1) {
						e1.printStackTrace();
					}	            		
					MenuFrame.this.display();			           
		        }					
		    } 
		);		
		panel.setLayout(new GridLayout(2, 2, 15, 15));
		frame.add(panel);
		
		btn_create.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				create.display();
			}			
		});
		
		btn_edit.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				edit.display();
			}			
		});
		
		btn_remove.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				remove.display();
			}			
		});
		
		btn_view.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				list.display();	
			}			
		});		
		frame.setVisible(true);		
	}

	@Override
	public void pokemonChanged(PokemonChangedEvent event) {
		this.pokedeckModel.setDeck(event.getListCards());
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

