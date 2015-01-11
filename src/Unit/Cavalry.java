package Unit;

import java.awt.Image;

public class Cavalry extends Unit
{
    public Cavalry()
    {
        //health, defense, attack, move, sight, range, picture
        super(115, 100, 150, 10, 11, 1, null);
    }
    
    /*
     * Constructor intended for use by extension of the Cavalry class (special instances or for use with interfaces)
     */
    public Cavalry(int health, int defense, int attack, int move, int sight, int range, Image picture)
    {
        super(health, defense, attack, move, sight, range, picture);
    }
    public String toBasicRep()
    {
        return "c";
    }
}