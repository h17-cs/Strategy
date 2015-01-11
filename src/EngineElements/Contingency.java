package EngineElements;

public class Contingency
{
	TriggerEvent[] triggers;
	UnitEvent[] effects;
	private boolean state;
	public Contingency(TriggerEvent[] triggers, UnitEvent[] effects)
	{
		this.triggers = triggers;
		this.effects = effects;
		for(TriggerEvent te : triggers)
			te.chain(this);
		for(UnitEvent ue : effects)
			ue.chain(this);
		state = false;
	}
	public boolean activate(Object source)
	{
		if (state)
			return !state;
		else
		{
			for(UnitEvent ue : effects)
				ue.trip(source);
			state = true;
			return state;
		}
	}

}
