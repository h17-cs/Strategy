package Unit;

import java.awt.Image;

public class Builder extends Infantry {

	public Builder() {
		// TODO Auto-generated constructor stub
	}

	public Builder(int health, int defense, int attack, int move, int sight,
			int range, Image picture) {
		super(health, defense, attack, move, sight, range, picture);
		// TODO Auto-generated constructor stub
	}
	public String toBasicRep()
	{
		return "r";
	}
}
