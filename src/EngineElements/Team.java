package EngineElements;

import java.awt.Color;
import java.util.ArrayList;

import Unit.Unit;

public class Team
{
	/*example team colors that I like*/
    private static Color deepGreen = 
        new Color(25, 97, 46);
    private static Color purple = 
        new Color(85, 26, 139);
    private static Color orange =
        new Color(255, 100, 0);
    /*Eight default teams*/
    static Team Team_One = new Team(Color.BLUE);
    static Team Team_Two = new Team(orange);
    static Team Team_Three = new Team(deepGreen);
    static Team Team_Four = new Team(purple);
    static Team Team_Five = new Team(Color.RED);
    static Team Team_Six = new Team(Color.YELLOW);
    static Team Team_Seven = new Team(Color.CYAN);
    static Team Team_Eight = new Team(Color.GREEN);
    
    private Color teamcolor;
    private ArrayList<Unit> team;
    private static Engine e;
    /*
     * constructs a team with the given color
     */
    public Team(Color color)
    {
        teamcolor = color;
        team = new ArrayList<Unit>();
    }
    /*
     * returns the team color
     */
    public Color getTeamColor()
    {
        return teamcolor;
    }
    /*
     * checks to see if the given unit is on this team
     */
    public boolean isOnTeam(Unit u)
    {
        return team.contains(u);
    }
    /* adds the given unit to the team
     * returns true if the unit was added,
     * returns false if the unit was not added(already on the team)
     */
    public boolean add(Unit unit)
    {
        if (team.contains(unit))
        	return true;
        team.add(unit);
        unit.setTeam(this);
        return false;
    }
    /* removes the given unit from the team
     * returns true if the unit was removed,
     * returns false if the unit was not removed(not on this team)
     */
    public boolean remove(Unit unit)
    {
    	if (team.contains(unit))
    	{
    		team.remove(team.indexOf(unit));
    		return true;
    	}
        return false;
    }
    /*
     * returns the team name
     */
    public String toString()
    {
        return e.findTeamName(this);
    }
    /*
     * initiates the game engine collectively used by all of the teams
     */
    public static void setEngine(Engine eng)
    {
        e = eng;
    }
}