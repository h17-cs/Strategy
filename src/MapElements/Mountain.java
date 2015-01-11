// A mountain is one of the three current tiles.

package MapElements;

import Unit.Unit;
import Unit.Infantry;
import Unit.Cavalry;

public class Mountain extends Tile
{
    public Mountain()
    {
        super(new Class[]{Infantry.class, Cavalry.class}, 50, 2, -3, null);
    }
    public void act()
    {
        setState(State.ACTIVE);
    }
    public void destroy()
    {
        setState(State.DAMAGED);
        setPassable(new Class[]{Unit.class});
    }
    public void settle()
    {
        setState(State.STATIC);
        setPassable(new Class[]{Infantry.class, Cavalry.class});
    }
    public String toBasicRep()
    {
        return "^";
    }
}