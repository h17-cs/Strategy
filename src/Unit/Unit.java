//Units are controllable aspects of the game. They include (so far):
//	infantry and cavalry for killing things,
//	the (prototype) Builder class capable of erecting buildings (W.I.P.)
//	the ranged interface for creating ranged units
//	Future additions:
//	The Building class, warehouses, unit generators, and research centers
//	The Magician interface for creating magic-wielding units
//
//All Units have the (tentative) stats of Health, Strength(Attack power), Defense, Movement speed,
//  Sight range, Attack range, and (in the future) an Image corresponding to the Unit
//Units (for now) have a temporary, resettable Attack and Defense for augmentation, penalties, terrain modifiers, etc.
//(^This will probably be modified or replaced^)

package Unit;

import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;

import EngineElements.Team;
import MapElements.Map;
import MapElements.Tile;

public abstract class Unit
{
    private int health, def, instDef, att, instAttack, move, sight, range, facing;
    private Color color;
    private Team myTeam;
    private Image pic;
    private Tile tile;
    private Map map;
    public Unit(int health, int defense, int strength, int move, int sight, int range, Image pic)
    {
        this.health = health;
        def = defense;
        instDef = def;
        att = strength;
        instAttack = strength;
        this.pic = pic;
        tile = null;
        map = null;
    }
    public int getHealth()
    {
        return health;
    }
    public int heal(int amt)
    {
        health += amt;
        return health;
    }
    public int harm(int amt)
    {
        health -= amt;
        return health;
    }
    public int getDefense()
    {
        return instDef;
    }
    public int buffDefense(int percentage)
    {
        instDef *= percentage;
        instDef /= 100;
        return instDef;
    }
    public int getStrength()
    {
        return instAttack;
    }
    public int buffStrength(int percentage)
    {
        instAttack *= percentage;
        instAttack /= 100;
        return instAttack;
    }
    public int getMove()
    {
        return move;
    }
    public int getSight()
    {
        return sight;
    }
    public int getRange()
    {
        return range;
    }
    public void resetBuffs()
    {
        instAttack = att;
        instDef = def;
    }
    public void putSelfOnTile(Tile t, Map m)
    {   
        Unit u = m.getUnitAt(t);
        if (u == null)
        {
            tile = t;
            map = m;
            m.add(this, t);
        }
        else
        {
            
        }
    }
    public Tile getTile()
    {
        return tile;
    }
    public Map getMap()
    {
        return map;
    }
    public abstract String toBasicRep();
    public void setColor(Color c)
    {
        color = c;
    }
    public Color getColor()
    {
        return color;
    }
    public void setTeam(Team team)
    {
        myTeam = team;
        setColor(myTeam.getTeamColor());
    }
    public Team getTeam()
    {
        return myTeam;
    }
    public String toString()
    {
        String name = getClass().getName();
        String tileName = (getTile().getClass()).getName();
        tileName = tileName.substring(tileName.lastIndexOf(".") + 1);
        return name.substring(name.lastIndexOf(".") + 1) + " (" + getHealth() + "), \n" + getTile().getCoord()
                + " \nAttack: " + getStrength() + ", \nDefense: " + getDefense() + ", \non a " + tileName;
    }
    
    //==========================
    //==== WORK IN PROGRESS ====
    //==========================
    
    private int currentmove;
    private int a;
    public void startTurn()
    {
    	currentmove = move;
    }
    public boolean turnLeft()
    {
    	if (currentmove > 0)
    	{
    		facing = (facing + 7) / 4;
            return true;
    	}
    	else return false;
    }
    public boolean turnRight()
    {
    	if (currentmove > 0)
    	{
    		facing = (facing + 9) / 4;
            return true;
    	}
    	else return false;
    }
    public boolean canMoveForward()
    {
    	return map.getTileAt(getTile().getCoord().getNext(facing)).getMovementModifier() < currentmove;
    }
    public boolean moveForward()
    {
    	Tile t = map.getTileAt(getTile().getCoord().getNext(facing));
    	if (t.getMovementModifier() < currentmove)
    	{
    		currentmove -= t.getMovementModifier();
    		return true;
    	}
        return false;
    }
    public ArrayList<Tile> getPosssibleMoveLocations()
    {
    	int temp = currentmove;
    	ArrayList<Tile> moves = new ArrayList<Tile>(4){
    				public boolean add(Tile t){
						if (!contains(t))
							return super.add(t);
						else return false;}};
    	while (true)
    	{
    		currentmove = move;
    		while (currentmove > 0)
    		{
    			Tile t = map.getTileAt(getTile().getCoord().getNext(facing));
    			int tempfacing = facing;
    			do {
    				if (canMoveForward())
        			{
        				moves.add(t);
        				moveForward();
        			}
        			else turnLeft();
    			} while (tempfacing != facing);
    		}
    	}
        //return null;
    }
    public boolean moveTo(Tile t)
    {
        return false;
    }
    public void attack(Unit u)
    {
        
    }
}
