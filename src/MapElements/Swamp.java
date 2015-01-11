// A swamp is one of the three current tiles

package MapElements;

import Unit.Infantry;

public class Swamp extends Tile
{
    public Swamp()
    {
        super(new Class[]{Infantry.class}, 30, -1, -3, null);
    }
    
    
    public void act()
    {
        setState(State.ACTIVE);
    }
    public void settle()
    {
        setState(State.STATIC);
        setPassable(new Class[]{Infantry.class});
    }
    public void destroy()
    {
        setState(State.DAMAGED);
        setPassable(null);
    }
    public String toBasicRep()
    {
        return "w";
    }
}