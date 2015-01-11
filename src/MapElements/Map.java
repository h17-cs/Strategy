package MapElements;

import java.awt.Color;
import java.awt.Image;

import Unit.Unit;

public class Map
{
    public Tile[][] content;
    public Unit[][] units;
    /*
     * constructs a default Map of the given dimensions of regular Plains tiles
     */
    public Map(int rows, int cols)
    {
        content = new Tile[rows][cols];
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
            {
                content[i][j] = new Plains();
                content[i][j].setCoordinate(new Coordinate(i, j));
            }
        units = new Unit[rows][cols];
    }
    /*
     * constructs a Map with the given array of Tiles
     */
    public Map(Tile[][] content)
    {
        this.content = content;
        for(int i = 0; i < content.length; i++)
            for(int j = 0; j < content[i].length; j++)
            content[i][j].setCoordinate(new Coordinate(i, j));
        units = new Unit[content.length][];
        for (int i = 0; i < content.length; i++)
        units[i] = new Unit[content[i].length];
    }
    /*
     * default 75 x 75 Map constructor
     */
    public Map()
    {
        this(75, 75);
    }
    /*
     * Hesitant constructor-
     * 
     * Creates a Map given an array of Tile classes.
     * (All Tiles should have a no-args constructor, but in case they do not,
     * I included a method that creates default values for parameters
     */
    public Map(Class[][] classes)
    {
        content = new Tile[classes.length][];
        for(int i = 0; i < classes.length; i++)
        {
            content[i] = new Tile[classes[i].length];
            for(int j = 0; j < classes[i].length; j++)
            try
            {
                content[i][j] = (Tile)classes[i][j].newInstance();
                content[i][j].setCoordinate(new Coordinate(i, j));
            }
            catch(Exception ex)
            {
                Class c = classes[i][j];
                Class c1 = c;
                do
                {
                    c1 = c1.getSuperclass();
                } while (c1 != Tile.class && c1 != Object.class);
                if (c1 == Tile.class){
                try
                {
                    Class[] types = c.getConstructor().getParameterTypes();
                    Object[] values = new Object[types.length];

                    for (int k = 0; k < types.length; k++)
                    {
                        values[k] = makeDefaultValue(types[k]);
                    }
                    c.getConstructor().newInstance(values);
                    content[i][j].setCoordinate(new Coordinate(i, j));
                }
                catch(Exception exc)
                {                    
                    content[i][j] = new Plains();
                    content[i][j].setCoordinate(new Coordinate(i, j));
                }}
                else                
                {
                    content[i][j] = new Plains();
                    content[i][j].setCoordinate(new Coordinate(i, j));
                }
            }
        }
        
        units = new Unit[content.length][];
        for (int i = 0; i < content.length; i++)
        units[i] = new Unit[content[i].length];
    }
    public Tile[][] getContent()
    {
        return content;
    }
    public Unit[][] getUnits()
    {
        return units;
    }
    private Object makeDefaultValue(Class type)
    {
        if (type == int.class)
            return new Integer(0);
        else if (type == boolean.class)
            return Boolean.FALSE;
        else if (type == double.class)
            return new Double(0);
        else if (type == String.class)
            return "";
        else if (type == Color.class)
            return Color.BLACK;
        else if (type == Tile.class)
            return new Plains();
        else
        {
            try
            {
                return type.newInstance();
            }
            catch (Exception ex)
            {
                return null;
            }
        }
    }
    public Tile getTileAt(Coordinate c)
    {
        return content[c.getRow()][c.getCol()];
    }
    public Unit getUnitAt(Tile t)
    {
        return units[t.getCoord().getRow()][t.getCoord().getCol()];
    }
    public Unit add(Unit u, Tile t)
    {
        Unit old = getUnitAt(t);
        units[t.getCoord().getRow()][t.getCoord().getCol()] = u;
        return old;
    }
    public Unit remove(Tile t)
    {
        Unit old = getUnitAt(t);
        units[t.getCoord().getRow()][t.getCoord().getCol()] = null;
        return old;
    }
    public Tile remove(Coordinate c)
    {
        Tile old = getTileAt(c);
        content[c.getRow()][c.getCol()] = null;
        return old;
    }
}