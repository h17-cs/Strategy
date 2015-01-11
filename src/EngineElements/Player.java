package EngineElements;

import Unit.Unit;

public /*abstract*/ class Player
{
	private Team t;
	private String name;
	private boolean AI;
	private Engine e;
	public Player(Team myTeam, String userName, boolean CPU, Engine e)
	{
		t = myTeam;
		name = userName;
		AI = CPU;
		this.e = e;
	}
	public boolean isCPU()
	{
		return AI;
	}
	public boolean isMine(Unit u)
	{
		return t.isOnTeam(u);
	}
	public String toString()
	{
		return name;
	}
	public Engine getEngine()
	{
		return e;
	}
}
