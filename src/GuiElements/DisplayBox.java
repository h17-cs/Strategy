//DisplayBox is the basis for popup menus


package GuiElements;

import java.awt.*;

public class DisplayBox
{
    private static final int THICKNESS = 2, HEIGHT1 = 18, HEIGHT2 = 120, WIDTH = 100, MARGIN = 3, TEXT_SIZE = 12;
    String head, body;
    int upperBounds, leftBounds;
    private Color c;
    public DisplayBox(int top, int left, String title, String text, Color color)
    {
        head = title;
        body = text;
        upperBounds = top;
        leftBounds = left;
        c = color;
    }
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(new Font("SansSerif", Font.BOLD, TEXT_SIZE));
        g2.setColor(c);
        Rectangle frame = new Rectangle(leftBounds, upperBounds, WIDTH + THICKNESS*2, HEIGHT1 + HEIGHT2 + THICKNESS * 3);
        Rectangle titleBar = new Rectangle(leftBounds + THICKNESS, upperBounds + THICKNESS, WIDTH, HEIGHT1);
        Rectangle contentPane = new Rectangle(leftBounds + THICKNESS, upperBounds + HEIGHT1 + THICKNESS * 2, WIDTH, HEIGHT2);
        g2.fill(frame);
        g2.setColor(Color.BLACK);
        g2.fill(titleBar);
        g2.fill(contentPane);
        g2.setColor(c);
        g2.drawString(head, leftBounds + THICKNESS + MARGIN, upperBounds + THICKNESS + MARGIN + TEXT_SIZE);
        int x = leftBounds + THICKNESS + MARGIN;
        int y = upperBounds + THICKNESS + HEIGHT1 + THICKNESS + MARGIN + TEXT_SIZE;
        int temp1 = 0;
        int temp2 = 0;
        do 
        {
            temp2 = body.indexOf("\n", temp1 + 1);
            if (temp2 == -1)
            temp2 = body.length();
            g2.drawString(body.substring(temp1, temp2), x, y);
            y += TEXT_SIZE + MARGIN;
            temp1 = temp2;
        } while (temp2 > 0 && temp2 < body.length());
        //g2.drawString(body, temp1, temp2);
        g2.setColor(Color.WHITE);
    }
}