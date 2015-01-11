package Unit;

import java.awt.Image;

public class Infantry extends Unit
{
    public Infantry()
    {
        //Health, defense, attack, move, sight, range, picture
        super(100, 100, 150, 5, 7, 1, null);
    }
    
    /*
     * Constructor intended for use by extension of the Infantry class (special instances or for use with interfaces)
     */
    public Infantry(int health, int defense, int attack, int move, int sight, int range, Image picture)
    {
        super(health, defense, attack, move, sight, range, picture);
    }
    public String toBasicRep()
    {
        return "i";
    }
}