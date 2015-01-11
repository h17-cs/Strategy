//Tiles are geographical elements of the map; Units can occupy a tile, but not all units can go on all tiles.
//Cavalry cannot enter swamps, buildings can't be easily built in mountains or swamps, etc.
//Tiles have a state, which affects how they behave. For instance, when you damage a mountain, you can allow for easier travel with some units, 
//and can allow for construction or passage of normally impassable terrain. Changing the state of a tile will eventually affect its graphical
//representation, and have some other effects. Tiles currently have a couple modifiers to the Units standing on them: a cover-based
//defense bonus, a range modifier, and a movement modifier (because some terrain takes extra time to pass through)
package MapElements;

import java.awt.Image;
enum State{STATIC, ACTIVE, DAMAGED;}
public abstract class Tile
{
    Class[] passable;
    int cover, range, move;
    Image pic;
    State currentState;
    Coordinate coord;
    
    public Tile(Class[] allowedUnits, int coverBonus, int rangeModifier, int movementModifier, Image image)
    {
        passable = allowedUnits;
        cover = coverBonus;
        range = rangeModifier;
        move = movementModifier;
        pic = image;
        currentState = State.STATIC;
        coord = null;
    }
    public void setCoordinate(Coordinate c)
    {
        coord = c;
    }
    public Coordinate getCoord()
    {
        return coord;
    }
    public Class[] passable()
    {
        return passable;
    }
    public void setPassable(Class[] newPass)
    {
        passable = newPass;
    }
    public int getCoverBonus()
    {
        return cover;
    }
    public int getRangeModifier()
    {
        return range;
    }
    public int getMovementModifier()
    {
        return move;
    }
    public Image toImage()
    {
        return pic;
    }
    protected void setImage(Image i)
    {
        pic = i;
    }
    public State getState()
    {
        return currentState;
    }
    public abstract void act();
    public abstract void destroy();
    public abstract void settle();
    public abstract String toBasicRep();
    protected void setState(State s)
    {
        currentState = s;
    }
    public String toString()
    {
    	String name = getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1) + ", \n" + currentState;
    }
}
