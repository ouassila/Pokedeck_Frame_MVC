package Model;
/**
 * Permet de distinguer la catégorie de cartes concernées
 * @author Ouassila
 *
 */

public abstract class CardVisitor {
	
	public abstract void visit(Pokemon p);
	public abstract void visit(TrainerCard t);
	public abstract void visit(EnergyCard e);
}
