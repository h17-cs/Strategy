package EngineElements;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

import MapElements.Coordinate;
import MapElements.Map;
import MapElements.Mountain;
import MapElements.Plains;
import MapElements.Swamp;
import MapElements.Tile;
import Unit.Unit;
import Unit.Cavalry;
import Unit.Infantry;
import Unit.Building;
import GuiElements.MapDisplay;

public class Engine
{
    private MapDisplay display;
    private Map map;
    private Object[][] teams;
    /*
     * creates a new game engine given
     *	 the MapDisplay (GUI aspect),
     *	 the map on which the game occurs,
     *	 the teams in play,
     * and assigns default team names.
     */
    public Engine(MapDisplay display, Map map, Team[] teams)
    {
        this.display = display;
        this.map = map;
        Object[][] roster = new Object[teams.length][2];
        for (int i = 0; i < teams.length; i++)
        {
            roster[i][0] = teams[i];
            roster[i][1] = "Team " + i;
        }
        this.teams = roster;
    }
    /*
     * creates a new game given
     *	 the MapDisplay (GUI aspect),
     *	 the map on which the game occurs,
     *	 the complete roster of teams in play (names included)
     */
    public Engine(MapDisplay display, Map map, Object[][] roster)
    {
        this.display = display;
        this.map = map;
        teams = roster;
    }
    /*
     * returns the String name of the given team.
     */
    public String findTeamName(Team team)
    {
        for (int i = 0; i < teams.length; i++)
        {
            Team t = (Team)teams[i][0];
            if (t == team)
            {
                return (String)teams[i][1];
            }
        }
        return "Independent";
    }
    /*
     * sets the current team roster. the order of teams and names should match.
     */
    public void setTeamRoster(Team[] teamlist, String[]  names)
    {
        teams = new Object[teams.length][2];
        for (int i = 0; i < teams.length; i++)
        {
            teams[i][0] = teamlist[i];
            teams[i][1] = names[i];
        }
    }
    /*
     * Parses a Map from a given text file, using the given teams.
     */
    public static Map parseMap(File file, Object[][] roster)
    {
    	Scanner scan = null;
        try
        {
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("FILE NOT FOUND");
            System.exit(0);
        }
        
        int rows = Integer.parseInt(scan.nextLine());
        int cols = Integer.parseInt(scan.nextLine());
        Tile[][] Tiles = new Tile[rows][cols];
        Unit[][] Units = new Unit[rows][cols];
        for (int i = 0; i < rows; i++)
        {
            String line = scan.nextLine();
            for(int j = 0; j < cols; j++)
            {
                char c = line.charAt(j);
                switch(c)
                {
                    case 'i':
                    case 'I':
                        Units[i][j] = new Infantry();
                        Tiles[i][j] = new Plains();                        
                        break;
                    case 'c':
                    case 'C':
                        Units[i][j] = new Cavalry();
                        Tiles[i][j] = new Plains();
                        break;
                    case '{':
                    case '}':
                    case ']':
                    case '[':
                    	Units[i][j] = new Building();
                        Tiles[i][j] = new Plains();
                    case 'm':
                    case 'M':
                        Tiles[i][j] = new Mountain();
                        break;
                    case 's':
                    case 'S':
                        Tiles[i][j] = new Swamp();
                        break;
                    case 'p':
                    case 'P':
                    default:
                        Tiles[i][j] = new Plains();
                }
            }
        }
        Map map = new Map(Tiles);
        /*creates example teams (temporary code used to test the Team class)*/
        int counter = 0;
        for (int i = 0; i < rows; i++)
        {
        	
        	for (int j = 0; j < cols; j++)
        	{
        		if (Units[i][j] != null)
        		{
        			Units[i][j].putSelfOnTile(map.getTileAt(new Coordinate(i, j)), map);
        			((Team)roster[counter][0]).add(map.getUnits()[i][j]);
        			counter++;
        			if (counter == 8)
        				counter = 0;
        		}
        	}
        }
        return map;
    	
    }
    /*
     * Initiates a new game from a given File (.txt) and an Object double-array ({{Team, String},...}) and starts gameplay.
     */
    public static void initiateGame(File gamefile, Object[][] roster)
    {
    	JFrame frame = new JFrame();
        
        Map map = parseMap(gamefile, roster);
        MapDisplay md = new MapDisplay(map);
        
        Engine e = new Engine(md, map, roster);
        Team.setEngine(e);
        
        
        
        frame.setContentPane(md);
        frame.setSize(1500, 900);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    	
    }
    /*
     * example game initiation
     */
    public static void main(String[] args)
    {
        initiateGame(new File("C:\\Users\\Charles_Hill\\workspace\\Strategy\\Valley.txt"), new Object[][]{
            {Team.Team_One, "Team One"},
            {Team.Team_Two, "Team Two"},
            {Team.Team_Three, "Team Three"},
            {Team.Team_Four, "Team Four"},
            {Team.Team_Five, "Team Five"},
            {Team.Team_Six, "Team Six"},
            {Team.Team_Seven, "Team Seven"},
            {Team.Team_Eight, "Team Eight"}});
    }
}