package Model;
import java.util.EventListener;
/**
 * Notifie la classe PokemonChangedEvent lors d'une action dans le pokedeck
 * @author Ouassila
 *
 */
public interface PokeListener extends EventListener{
	public void pokemonChanged(PokemonChangedEvent event);
}
