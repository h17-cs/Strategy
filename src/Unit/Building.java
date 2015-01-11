package Unit;

import java.awt.Image;

public class Building extends Unit
{
	public Building()
	{
		super(200, 250, 0, 0, 8, 1, null);
	}
	public Building(int health, int defense, int strength, int move, int sight,
			int range, Image pic)
	{
		super(health, defense, strength, move, sight, range, pic);
	}

	@Override
	public String toBasicRep()
	{
		return "[]";
	}

}
