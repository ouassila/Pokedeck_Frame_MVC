package Model;

public class Pokemon extends Card{
	
	private static final long serialVersionUID = 10L;
	private String description, type;
	private int number, stage, HP;
	
	public Pokemon(String name, String description, String type, int number, int stage, int hp){
		super(name);
		this.description=description;
		this.HP=hp;
		this.number=number;
		this.stage=stage;
		this.type=type;
	}
	
	@Override
	public String toString() {
		  return "<html> HP: "+this.HP+"<br> Number : "+this.number+"<br> Stage : "+this.stage+"<br> Type : "+this.type+"<br> Description : "+this.description +"</html>";
	}
	
	@Override
	public void updateCard(int indexLabel, String value) {
		switch(indexLabel){
		case 1:
			this.type=value;
			break;
		case 2:
			this.HP=Integer.parseInt(value);
			break;
		case 3:
			this.stage=Integer.parseInt(value);
			break;
		case 4:
			this.number=Integer.parseInt(value);
			break;
		case 5:
			this.description=value;
			break;
		case 6:
			this.name=value;
			break;		
		}
		
	}
	@Override
	 public void accept(CardVisitor v) {
	    v.visit(this);
	  }
}
