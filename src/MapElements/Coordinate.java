//Coordinate is the base class for the Coordinate system in this game. It is used from finding units at a specific address to drawing the map.
package MapElements;

import java.util.ArrayList;

public class Coordinate
{
    int y, x;
    public Coordinate(int row, int col)
    {
        y = row;
        x = col;
    }
    /*
     * returns the absolute distance from one Coordinate to another
     */
    public double getDistanceFrom(Coordinate other)
    {
        return Math.sqrt((other.y - y) * (other.y - y) + (other.x - x) * (other.x - x));
    }
    public int getRow()
    {
        return y;
    }
    public int getCol()
    {
        return x;
    }
    public ArrayList<Coordinate> getAllCoordinatesWithinDistance(int r)
    {
        ArrayList<Coordinate> coords = new ArrayList<Coordinate>((int)(Math.PI * r * r) + 1);
        for (int i = getRow() - r; i < getRow() + r; i++)
        {
            for (int j = getCol() - r; j < getCol() + r; j++)
            {
                Coordinate coord = new Coordinate(i, j);
                if (i > 0 && j > 0 && getDistanceFrom(coord) < r)
                coords.add(coord);
            }
        }
        return coords;
    }
    /*
     * direction is 0-7.
     */
    public Coordinate getNext(int direction)
    {
    	switch(direction)
    	{
    	case 0:
    		return new Coordinate(y - 1, x);
    	case 1:
    		return new Coordinate(y - 1, x + 1);
    	case 2:
    		return new Coordinate(y, x + 1);
    	case 3:
    		return new Coordinate(y + 1, x + 1);
    	case 4:
    		return new Coordinate(y + 1, x);
    	case 5:
    		return new Coordinate(y + 1, x - 1);
    	case 6:
    		return new Coordinate(y, x - 1);
    	case 7:
    		return new Coordinate(y - 1, x - 1);
		default:
			return this;
    	}
    }
    public String toString()
    {
        return "at (" + y + ", " + x + ")";
    }
}
