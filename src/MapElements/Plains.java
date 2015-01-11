// A plain is one of the three current tiles

package MapElements;

import java.awt.Image;
import javax.swing.ImageIcon;

import Unit.Unit;

public class Plains extends Tile
{
    public static final Image still = (new ImageIcon("PlainsStatic.gif")).getImage(), 
    active = (new ImageIcon("PlainsActive.gif")).getImage(), 
    damaged = (new ImageIcon("PlainsDamaged.gif")).getImage();
    public Plains()
    {
        super(new Class[]{Unit.class}, 0, 0, 0, still);
    }
    public void act()
    {
        setImage(active);
        setState(State.ACTIVE);
    }
    public void destroy()
    {
        setImage(damaged);
        setState(State.DAMAGED);
    }
    public void settle()
    {
        setImage(still);
        setState(State.STATIC);
    }
    public String toBasicRep()
    {
        return ".";
    }
}