package EngineElements;

public abstract class GameEvent
{

	public GameEvent()
	{
		
	}
	public abstract void trip(Object source);
	public abstract int chain(Contingency c);
}
