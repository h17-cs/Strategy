//MapDisplay is the basis for GUI elements of the game; it draws the map and the units on it in the correct colors
package GuiElements;

import java.awt.*;

import javax.swing.*;

import MapElements.Coordinate;
import MapElements.Map;
import MapElements.Tile;
import Unit.Unit;
import EngineElements.Team;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MapDisplay extends JPanel
{
	/*
	 * Cell_Size is the height of the boxes that make up the grid-based coordinate system
	 * Top_Row is the y-value of the start of the topmost row of the grid (there's a buffer space above it for menus or text)
	 * Left_Col is the x-value of the start of the leftmost column of the grid (there's a buffer space to the left of it for menus or text)
	 * Bottom_Row is the y-value of the end of the bottom-most row of the grid (there's a buffer space below it for menus or text)
	 * Right_Col is the x-value of the end of the rightmost column of the grid (there's a buffer space to the right of it it for menus or text)
	 * Top_row, Left_Col, Bottom_Row, and Right_Col outline a box where the map is displayed (will become scrollable in the future)
	 * This box is composed of a grid of Coordinates Cell_Size tall and (Cell_Size / 2 + 1) thick.
	 */
    private final int CELL_SIZE = 8, TOP_ROW = 50, LEFT_COL = 50, BOTTOM_ROW = 1250, RIGHT_COL = 650;
    private final Map map;
    private Coordinate currentCoord;
    JPanel glassPane;
    JToolTip tip;
    Timer tipTimer;
    Graphics g;
    public MapDisplay(Map map)
    {
        this.map = map;
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
                Coordinate c = PointToCoord(evt.getPoint());
                setCurrentCoord(c);
                // showTip(getToolTipText(currentCoord), CoordToPoint(currentCoord));
                repaint();
            }
        });
    }
    /*
     * paints the current grid, including the DisplayBox for the current coordinate
     */
    public void paintComponent(Graphics g)
    {
        this.g = g;
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        setBackground(Color.BLACK);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.BOLD, CELL_SIZE));
        Tile[][] tiles = map.getContent();
        Unit[][] units = map.getUnits();
        Rectangle frame = new Rectangle(TOP_ROW, LEFT_COL, BOTTOM_ROW - TOP_ROW, RIGHT_COL - LEFT_COL);
        g2.drawRect(TOP_ROW, LEFT_COL, BOTTOM_ROW - TOP_ROW, RIGHT_COL - LEFT_COL);
        Rectangle drawableArea = new Rectangle(TOP_ROW, LEFT_COL, CELL_SIZE / 2 + 1, CELL_SIZE);
        int count1 = 0, count2 = 0, max1, max2;
        max2 = units[0].length;
        max1 = units.length;
        for (int i = TOP_ROW; i < BOTTOM_ROW && count2 < max2; i += CELL_SIZE / 2 + 1)
        {
            max2 = units[count1].length;
            for(int j = LEFT_COL + CELL_SIZE; j < RIGHT_COL + CELL_SIZE && count1 < max1; j+= CELL_SIZE)
            {
                if (count1 < max1 && count2 < max2 && units[count1][count2] != null)
                {
                    g2.setColor(units[count1][count2].getTeam().getTeamColor());
                    g2.drawString(units[count1][count2].toBasicRep(), i, j);
                }
                else
                {
                    g2.setColor(Color.WHITE);
                    g2.drawString(tiles[count1][count2].toBasicRep(), i, j);
                }
                count1++;
            }
            count1 = 0;
            count2++;
        }
        
        if (getCurrentCoord() != null)
        {
            String name = "";
            Tile t; Unit u;Color color;
            if ((u = map.getUnitAt(map.getTileAt(getCurrentCoord()))) != null)
            {name = "" + u.getClass().getName(); color = u.getColor();}
            else if ((t = map.getTileAt(getCurrentCoord())) != null)
            {name = "" + t.getClass().getName(); color = Color.WHITE;}
            else
            {name = "" + getCurrentCoord().getClass().getName(); color = Color.WHITE;}
            name = name.substring(name.lastIndexOf(".") + 1);
            
            String body = getToolTipText(getCurrentCoord());
            DisplayBox bx = new DisplayBox(CoordToPoint(getCurrentCoord()).y, CoordToPoint(getCurrentCoord()).x, name, body, color);
            bx.paint(g);
        }
    }
    /*
     * creates tooltip text for a mouse event (wherever you click)
     * not currently used, but might be used later on.
     */
    public String getToolTipText(MouseEvent evt)
    {
        Coordinate c = PointToCoord(evt.getPoint());
        return getToolTipText(c);
    }
    /*
     * creates tooltip text for a given coordinate
     */
    private String getToolTipText(Coordinate crd)
    {
        if (crd == null)
            return null;
        Tile f = map.getTileAt(crd);
        if (f != null)
        {
            Unit g = map.getUnitAt((Tile)f);
            if (g != null)
            return "" + g.getTeam() + ": \n" + g /*+ "\n" + f + "\n\n" + crd*/;
            else
            return "" + f /*+ "\n\n" + crd*/;
        }
        else
        {
            return "" + crd;
        }
    }
    /*
     * dislays a tooltip at the given point with the given text
     * not currently used, but might be used later on.
     */
    public void showTip(String tipText, Point pt)
    {
        if (getRootPane() == null)
            return;
        if (glassPane == null)
        {
            getRootPane().setGlassPane(glassPane = new JPanel());
            glassPane.setOpaque(false);
            glassPane.setLayout(null); 
            glassPane.setBackground(Color.BLACK);
            glassPane.add(tip = new JToolTip());
            
            tipTimer = new Timer(5000, new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    glassPane.setVisible(false);
                }
            });
            tipTimer.setRepeats(false);
        }
        if (tipText == null)
            return;
        tip.setTipText(tipText);
        tip.setLocation(SwingUtilities.convertPoint(this, pt, glassPane));
        tip.setSize(tip.getPreferredSize());
        glassPane.setVisible(true);
        glassPane.repaint();
        tipTimer.restart();
    }
    /*
     * translates a Point into the Coordinate it lies in
     */
    public Coordinate PointToCoord(Point p)
    {
        int y = p.y;
        int x = p.x;
        return new Coordinate(yToRow(y), xToCol(x));
    }
    /*
     * translates a Coordinate to the Point in its bottom-right corner
     */
    public Point CoordToPoint(Coordinate c)
    {
        return new Point(CxToPx(c.getCol()), CyToPy(c.getRow()));
    }
    /*
     * sets the active coordinate (for DisplayBox and future use)
     */
    private void setCurrentCoord(Coordinate c)
    {
        if (c != null && c.getRow() != -1 && c.getCol() != -1)
            currentCoord = c;
        else
            currentCoord = null;
    }
    /*
     * returns the active coordinate
     */
    private Coordinate getCurrentCoord()
    {
        return currentCoord;
    }
    /*
     * translates a Point's x-value into a Coordinate's x-value.
     */
    public int xToCol(int x)
    {
        int Col = (x - LEFT_COL + 1) / (CELL_SIZE / 2 + 1);
        if (Col >= 0 && Col < map.getContent()[0].length)
        return Col;
        // else if (Col >= map.getContent()[0].length)
        // return map.getContent()[0].length;
        // else
        // return 0;
        else return -1;
    }
    /*
     * translates a Point's y-value into a Coordinate's y-value.
     */
    public int yToRow(int y)
    {
        int Row = (y - TOP_ROW + 1) / CELL_SIZE;
        
        if (Row > 0 && Row < map.getContent().length)
        return Row;
        // else if (Row >= map.getContent().length)
        // return map.getContent().length;
        // else
        // return 0;   
        else return -1;
    }
    /*
     * translates a Coordinate's x-value into a Point's x-value
     * (the Point is the bottom-right corner of the Coordinate box)
     */
    public int CxToPx(int col)
    {
        if (col == -1)
        return -1;
        else
        return (col + 1) * (CELL_SIZE / 2 + 1) + LEFT_COL;
    }
    /*
     * translates a Coordinate's y-value into a Point's y-value
     * (the Point is the bottom-right corner of the Coordinate box)
     */
    public int CyToPy(int row)
    {
        if (row == -1)
        return -1;
        else
        return (row + 1) * CELL_SIZE + TOP_ROW;
    }
}