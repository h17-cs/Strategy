package EngineElements;

import java.util.ArrayList;

public class UnitEvent extends GameEvent
{
	private ArrayList<Contingency> links;
	private UnitEffects eff;
	public UnitEvent(UnitEffects effects)
	{
		links = new ArrayList<Contingency>();
		eff = effects;
	}
	public int chain(Contingency c)
	{
		links.add(c);
		return links.size();
	}
	public void trip(Object source)
	{
		/*eff.act();*/
	}
}
