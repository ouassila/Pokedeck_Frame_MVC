package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.PokemonControleur;
import Model.Card;
import Model.EnergyCard;
import Model.Pokemon;
import Model.PokemonChangedEvent;
import Model.TrainerCard;
/**
 * Affichage du module de création de cartes
 * @author Ouassila
 *
 */
public class CreateView extends PokemonView{
	JPanel pan;
	public CreateView(PokemonControleur controleur) {
		super(controleur);		
		frame  = addWindow("Menu : Create card");
		this.create_start();
		
	}

	public void create_start(){
			
		JPanel panel = new JPanel();	
		
	    JLabel label_name = this.addLabel(panel, "Name : ");
	    panel.add(label_name);
	    final JTextField input_name = this.addInput(panel);  
	    
	    JLabel label_type = this.addLabel(panel, "Type : ");
	    panel.add(label_type);           
	    final JComboBox<String> input_type = this.addComboTypeCard(panel);             
	   
	    panel.setLayout(new GridLayout (0, 2, 15, 15));  
	   
	    this.frame.setLayout(new BorderLayout()) ;
	    panel.setBorder(new EmptyBorder(60, 10, 0, 10));
	    
	    this.frame.add (panel , BorderLayout.NORTH);        
	    pan = new JPanel();
	    frame.add( pan , BorderLayout.CENTER);
    	pan.setVisible(true);
	    input_type.addActionListener (new ActionListener () {
			
			
		    public void actionPerformed(ActionEvent e) {		    	
		    	frame.remove(pan);		    	
		        if(input_type.getSelectedItem() == "Pokemon"){		        		
		        	pan = addPanelCreatePokemonCard(input_name.getText());	
		        }
		        else if(input_type.getSelectedItem() == "Trainer"){		        	
		        	pan = addPanelCreateTrainerCard(input_name.getText());
		        }
		        else if(input_type.getSelectedItem() == "Energy"){
		        	pan = addPanelCreateEnergyCard(input_name.getText());		     
		        }
		        else{
		        	pan=new JPanel();
		        }		       
		        frame.add( pan , BorderLayout.CENTER);
		        frame.validate();
			}			
		});
	    
	   
	}
	

	public JPanel addPanelCreateEnergyCard(final String name_card) {
		
		JPanel panel_button = new JPanel(); 
		JButton validate = this.addButton(panel_button, "Create") ;
		
		validate.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	Card card = new EnergyCard(name_card);				
				getControleur().notifyAddCard(card);	
				restartView();
				close();
	        }
	    });
		panel_button.add(validate);	
		return panel_button;
	}

	public JPanel addPanelCreateTrainerCard(final String name_card) {
		
		JPanel panel = new JPanel();
		JPanel panel_button = new JPanel(); 
		JButton validate = this.addButton(panel_button, "Create") ;
		
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.setLayout(new GridLayout (0, 2, 15, 15));
		
		JLabel label_desc = new JLabel("Description :");
		panel.add(label_desc);
		final JTextField input_desc = this.addInput(panel); 
		
		JLabel label_type = new JLabel("Type :");
		panel.add(label_type);
		final JTextField input_type = this.addInput(panel); 
		
		JLabel label_rule = new JLabel("Rule :");
		panel.add(label_rule);
		final JTextField input_rule = this.addInput(panel); 
		
		JLabel label_number = new JLabel("Number :");
		panel.add(label_number);
		final JTextField input_number = this.addInput(panel); 			
		
		validate.addActionListener(new ActionListener() {				
	        public void actionPerformed(ActionEvent e) {
	        	Pattern pattern = Pattern.compile("\\d");
	        	
	        	if(!pattern.matcher(input_number.getText()).find()){
	        		JOptionPane.showMessageDialog(frame, "The value of number must be an integer", 
						      "Error", JOptionPane.ERROR_MESSAGE);
	        	}
	        	else{
		        	Card card = new TrainerCard(input_desc.getText(), input_type.getText(), input_rule.getText(), name_card, Integer.parseInt(input_number.getText()));
		        	getControleur().notifyAddCard(card);	
		        	restartView();
					close();
	        	}
	        }
	    });
		
		panel_button.add(validate);
		panel.add ( panel_button , BorderLayout.SOUTH);	
		
		return panel;
	}

	public JPanel addPanelCreatePokemonCard(final String name_card) {
		
		JPanel panel = new JPanel();
		JPanel panel_button = new JPanel();
		
		JButton validate = this.addButton(panel_button, "Create") ; 		
		
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.setLayout(new GridLayout (0, 2, 15, 15));
		
		JLabel label_desc = new JLabel("Description :");
		panel.add(label_desc);
		final JTextField input_desc = this.addInput(panel); 
		
		JLabel label_type = new JLabel("Type :");
		panel.add(label_type);
		final JTextField input_type = this.addInput(panel); 
		
		JLabel label_stage = new JLabel("Stage :");
		panel.add(label_stage);
		final JTextField input_stage = this.addInput(panel); 
		
		JLabel label_hp = new JLabel("HP :");
		panel.add(label_hp);
		final JTextField input_hp = this.addInput(panel); 
		
		JLabel label_number = new JLabel("Number :");
		panel.add(label_number);
		final JTextField input_number = this.addInput(panel); 		
		
		validate.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Pattern pattern = Pattern.compile("\\d");
	          	 
	        	if(!pattern.matcher(input_number.getText()).find()){
	        		JOptionPane.showMessageDialog(frame, "The value of number must be an integer", 
						      "Error", JOptionPane.ERROR_MESSAGE);
	        	}
	        	else if(!pattern.matcher(input_hp.getText()).find()){
	        		JOptionPane.showMessageDialog(frame, "The value of hp must be an integer", 
						      "Error", JOptionPane.ERROR_MESSAGE);
	        	}
	        	else if(!pattern.matcher(input_stage.getText()).find()){
	        		JOptionPane.showMessageDialog(frame, "The value of stage must be an integer", 
						      "Error", JOptionPane.ERROR_MESSAGE);
	        	}
	        	else{
	        		Card card = new Pokemon(name_card,input_desc.getText(), input_type.getText(), Integer.parseInt(input_number.getText()), Integer.parseInt(input_stage.getText()), Integer.parseInt(input_hp.getText()));
		        	getControleur().notifyAddCard(card);
		        	restartView();
					close();
	        	}	        	
	        }
	    });

		panel_button.add(validate);		
		panel.add (panel_button , BorderLayout.SOUTH);	
		
		return panel;
	}

	@Override
	public void pokemonChanged(PokemonChangedEvent event) {		
	}

	@Override
	public void display() {
		frame.setVisible(true);
	}

	@Override
	public void close() {
		frame.dispose();
	}
	
	public void restartView(){
		frame.remove(pan);
		
	}
}
