package EngineElements;

import java.util.ArrayList;

public class TriggerEvent extends GameEvent
{
	ArrayList<Contingency> links;
	boolean active;
	public TriggerEvent()
	{
		links = new ArrayList<Contingency>();
		active = true;
	}
	public void trip(Object source)
	{
		if (active)
		for (Contingency c : links)
			if (c.activate(source));
	}
	public int chain(Contingency c)
	{
		if (active)
		{
			links.add(c);
			return links.size();
		}
		return 0;
	}
	public void scrap()
	{
		if (active)
		{
			links = null;
			active = false;
		}
	}
}
