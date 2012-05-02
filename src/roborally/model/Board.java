package roborally.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import roborally.basics.Position;

/**
 * Een klasse om borden voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @Invar 	Een board moet ten alle tijden een geldige hoogte en breedte hebben/
 * 			| isValidHeight(getHeight())
 *  		| isValidWidth(getWidth())
 *  
 * @version 1.1
 */
public class Board{
	//TODO: documentatie en annotations
	//TODO:	kan getermineerd worden moet de map dan op null gezet worden (bavo)

	/**
	 * De breedte van het bord.
	 */
	private final long width;
	/**
	 * De hoogte van het bord.
	 */
	private final long height;
	/**
	 * Indien het object vernietigd is wordt dit true.
	 */
	private boolean isTerminated = false;
	/**
	 * Deze HashMap houdt een lijst van HashSets bij per Position (als String) met in elke HashSet alle objecten op die plaats.
	 */
	private final HashMap <String, HashSet<Entity>> map;
	/**
	 * De maximale breedte die een bord kan hebben.
	 */
	private static final long UPPER_BOUND_WIDTH = Long.MAX_VALUE;
	/**
	 * De minimale breedte die een bord kan hebben.
	 */
	private static final long LOWER_BOUND_WIDTH = 0;
	/**
	 * De maximale hoogte die een bord kan hebben.
	 */
	private static final long UPPER_BOUND_HEIGHT = Long.MAX_VALUE;
	/**
	 * De minimale hoogte die een bord kan hebben.
	 */
	private static final long LOWER_BOUND_HEIGHT = 0;

	/**
	 * Deze constructor maakt een nieuw bord aan.
	 * 
	 * @param	height
	 * 			De hoogte van het nieuwe bord.
	 * 
	 * @param 	width
	 * 			De breedte van het nieuwe bord.
	 * 
	 * @post	Het bord heeft een map gekregen.
	 * 			|new.getMap() != null
	 * 
	 * @post	Het bord heeft een hoogte gekregen.
	 * 			|new.getHeight() == height
	 * 
	 * @post	Het bord heeft een breedte gekregen.
	 * 			|new.getWidth() == width
	 */
	public Board (long height, long width){
		this.height = height;
		this.width = width;
		this.map = new HashMap<>();
	}

	/**
	 * Deze methode geeft alle objecten terug op een positie.
	 * 
	 * @param	pos
	 * 			De positie waar gekeken moet worden.
	 * 
	 * @return	De HashSet met alle objecten op deze positie.
	 * 			|this.getMap().get(pos)
	 */
	public HashSet<Entity> getEntityOnPosition(Position pos){
		return this.getMap().get(pos.toString());
	}

	/**
	 * Deze methode verwijdert een object uit het bord.
	 * 
	 * @param 	ent
	 * 			De entity die verwijderd moet worden.
	 * 
	 * @throws	IllegalStateException
	 * 			Het object bevindt zich niet op een juiste positie in dit bord.
	 * 			|ent.getBoard().isValidBoardPosition(ent.getPosition())
	 */
	public void removeEntity(Entity ent) throws IllegalStateException{
		Position pos = ent.getPosition();
		if(!ent.getBoard().isValidBoardPosition(pos)){
			throw new IllegalStateException("Dit object bevindt zich niet op een geldige positie.");
		}
		this.getMap().get(pos.toString()).remove(ent);
		this.cleanBoardPosition(pos);
	}

	/**
	 * Deze methode kijkt na of het bord leeg is op deze positie, indien dat zo is wordt een mogelijke opgeslagen lege HashSet verwijderd.
	 * 
	 * @param	pos
	 * 			De positie in het bord die moet nagekeken worden.
	 * 
	 * @post	De positie is leeg indien er geen objecten meer stonden.
	 * 			|if(this.getMap().containsKey(pos.toString()) && this.getMap().get(pos.toString()).isEmpty())
	 * 			|	new.getMap().containsKey(pos.toString()) == false
	 */
	public void cleanBoardPosition(Position pos){
		if(this.getMap().containsKey(pos.toString())){
			if(this.getMap().get(pos.toString()).isEmpty()){
				this.getMap().remove(pos.toString());
			}
		}
	}

	/**
	 * Deze methode kijkt na of een object op een plaats geplaatst kan worden.
	 * 
	 * @param	pos
	 * 			De positie die moet nagekeken worden.
	 * 
	 * @return	Boolean die true is als de positie niet door een muur ingenomen is.
	 * 			|if(!this.getMap().containsKey(pos.toString()))
	 * 			|	true
	 * 			|if(this.getMap().get(pos.toString()).isEmpty())
	 * 			|	true
	 * 			|if (this.getEntityOnPosition(pos).size() == 1){
	 * 			|	if(this.getEntityOnPosition(pos).iterator().next() instanceof Wall)
	 * 			|		false
	 * 			|true
	 * 			
	 */
	public boolean isPlacableOnPosition(Position pos){
		if (!this.getMap().containsKey(pos.toString()))
			return true;
		if (this.getMap().get(pos.toString()).isEmpty())
			return true;
		if (this.getEntityOnPosition(pos).size() == 1){
			Entity obj = this.getEntityOnPosition(pos).iterator().next();
			if (obj instanceof Wall)
				return false;
		}
		return true;
	}

	/**
	 * Deze methode plaatst een object op het bord.
	 * 
	 * @param	key
	 * 			De positie waar het object geplaatst moet worden.
	 * 			
	 * @param 	entity
	 * 			Het object dat op het bord geplaatst moet worden.
	 * 
	 * @post	Als de positie op het bord beschikbaar was staat het object op deze positie.
	 * 			|if(this.isPlacableOnPosition(key))
	 * 			|	new.get(key.toString()) == entity
	 * 			
	 */
	public void putEntity(Position key, Entity entity){
		if (this.isPlacableOnPosition(key)){
			if (entity instanceof Battery || entity instanceof Robot){
				HashSet<Entity> set = this.getMap().get(key.toString());
				if (set == null){
					HashSet<Entity> input = new HashSet<>();
					input.add(entity);
					this.getMap().put(key.toString(), input);
				}else{
					set.add(entity);
				}
			}else if(entity instanceof Wall){
				HashSet<Entity> input = new HashSet<>();
				input.add(entity);
				this.getMap().put(key.toString(), input);
			}
		}
	}

	/**
	 * Deze methode geeft alle robots terug die op dit bord staan.
	 * 
	 * @return	Set<Robot>
	 * 			Voor iedere robot die een element is van this.getMap() geldt dat deze op het einde van deze methode in de set zal zitten die teruggegeven wordt. Deze set is null als er geen robots op het bord staan.
	 * 			
	 */
	public Set<Robot> getRobots() {
		Collection<HashSet<Entity>> c = this.getMap().values();
		HashSet<Robot> rob = new HashSet<>();
		for (Set<Entity> values : c ){
			Iterator<Entity> i = values.iterator();
			while (i.hasNext()){
				Entity obj = i.next();
				if (obj instanceof Robot){
					rob.add((Robot) obj);
				}
			}
		}
		return rob;
	}

	/**
	 * Deze methode geeft alle Batteries terug die op dit board staan.
	 * 
	 * @return	Set<Battery>
	 * 			Voor iedere batterij die een element is van this.getMap() geldt dat deze op het einde van deze methode in de set zal zitten die teruggegeven wordt. Deze set is null als er geen batterijen op het bord staan.
	 * 			
	 */
	public Set<Battery> getBatteries() {
		Collection<HashSet<Entity>> c = this.getMap().values();
		HashSet<Battery> bat = new HashSet<>();
		for (Set<Entity> values : c ){
			Iterator<Entity> i = values.iterator();
			while (i.hasNext()){
				Entity obj = i.next();
				if (obj instanceof Battery){
					bat.add((Battery) obj);
				}
			}

		}
		return bat;
	}

	/**
	 * Deze methode geeft alle Walls terug die op dit board staan.
	 * 
	 * @return	Set<Wall>
	 * 			Voor iedere muur die een element is van this.getMap() geldt dat deze op het einde van deze methode in de set zal zitten die teruggegeven wordt. Deze set is null als er geen muren op het bord staan.
	 * 			
	 */
	public Set<Wall> getWalls() {
		Collection<HashSet<Entity>> c = this.getMap().values();
		HashSet<Wall> wall = new HashSet<>();
		for (Set<Entity> values : c ){
			Iterator<Entity> i = values.iterator();
			while (i.hasNext()){
				Entity obj = i.next();
				if (obj instanceof Wall){
					wall.add((Wall) obj);
				}
			}

		}
		return wall;
	}

	/**
	 * Deze inspector geeft een boolean die true is wanneer de hoogte toegestaan is en in het andere geval false.
	 * 
	 * @param 	height
	 * 	
	 * @return	boolean 
	 * 			|if ((height > Board.LOWER_BOUND_HEIGHT) && (height < Board.UPPER_BOUND_HEIGHT))
	 * 			|	true
	 */
	@Basic
	public static boolean isValidHeight(long height){
		return (height > LOWER_BOUND_HEIGHT) && (height <= UPPER_BOUND_HEIGHT);
	}

	/**
	 * Inspector om na te gaan of een gegeven breedte width een toegstane waarde is.
	 * 
	 * @param 	width
	 * 		
	 * @return	boolean
	 * 			|((width > Board.LOWER_BOUND_WIDTH) && (width < Board.UPPER_BOUND_WIDTH))
	 * 			|	true
	 */
	@Basic
	public static boolean isValidWidth(long width){
		return (width > LOWER_BOUND_WIDTH) && (width <= UPPER_BOUND_WIDTH);
	}

	/**
	 * Inspector die de breedte van dit bord teruggeeft.
	 * 
	 * @return	|this.width
	 */
	@Basic
	@Immutable
	public long getWidth() {
		return this.width;
	}

	/**
	 * Inspector die de hoogte van dit bord teruggeeft.
	 * 
	 * @return	|this.height
	 */
	@Basic
	@Immutable
	public long getHeight() {
		return this.height;
	}

	/**
	 * Deze methode kijkt na of een positie op het bord geldig is.
	 * 
	 * @param	position
	 * 			De positie waarop nagekeken moet worden.
	 * 
	 * @return	|if (position.getX() > this.getWidth() || position.getX() < LOWER_BOUND_WIDTH || position.getY() > this.getHeight() || position.getY() < LOWER_BOUND_HEIGHT)
	 * 			|	false
	 * 			|true
	 */
	@Basic
	public boolean isValidBoardPosition(Position position){
		if(position == null)
			return false;
		if (position.getX() > this.getWidth() || position.getX() < LOWER_BOUND_WIDTH || position.getY() > this.getHeight() || position.getY() < LOWER_BOUND_HEIGHT)
			return false;
		return true;
	}
	
	/**
	 * Deze methode geeft de map terug waarop dit bord gebaseerd is.
	 * 
	 * @return	|this.map
	 */
	public HashMap<String, HashSet<Entity>> getMap() {
		return this.map;
	}

	public void merge(Board board2) {
		// TODO methode om 2 borden samen te voegen
		HashMap<String, HashSet<Entity>> possessions = board2.getMap();
		Set<String> keys = possessions.keySet();
		for (String key : keys){
			HashSet<Entity> pos = possessions.get(key);
			if (this.getMap().containsKey(key)){
				HashSet<Entity> mapset = this.getMap().get(key);
				if ((mapset == null) || (mapset.size() == 0)){
					this.getMap().put(key, pos);
				} else if (mapset.size() == 1){
					Entity ent = mapset.iterator().next();
					if (!(ent instanceof Wall)){
						this.getMap().put(key, pos);
					}
				}
			}else{
				if ((this.getWidth() >= pos.iterator().next().getPosition().getX()) || (this.getHeight() >= pos.iterator().next().getPosition().getY())){
					this.getMap().put(key, pos);
				}
			}
		}
		board2.terminate();
	}

	public boolean isTerminated(){
		return this.isTerminated;
	}
	public void terminate(){
		this.isTerminated = true;
		Collection<HashSet<Entity>> c = this.getMap().values();
		for (HashSet<Entity> ents : c){
			for (Entity ent : ents){
				Position pos = ent.getPosition();
				ent.destroy();
				this.cleanBoardPosition(pos);
			}
		}
		
	}
}
