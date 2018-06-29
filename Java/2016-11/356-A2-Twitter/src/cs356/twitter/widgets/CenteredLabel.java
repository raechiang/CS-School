/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.widgets;

//-----------------------------------imports------------------------------------
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * This method is a simple Button. It's really just a JButton but made so that a
 * JLabel can be created in a more compact way and added to a JFrames JPanel
 * more easily.
 */
public class CenteredLabel implements Widget
{
//-----------------------------------field--------------------------------------
    /**
     * The JLabel
     */
    private JLabel label;
    
//---------------------------------constructor----------------------------------
    /**
     * This constructor sets up the {@link #label} in one declaration.
     * @param name
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public CenteredLabel(String name, int x, int y, int width, int height)
    {
        label = new JLabel(name);
        label.setBounds(x, y, width, height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
//-----------------------------------methods------------------------------------
    /**
     * Simple getter for {@link #label}.
     */
    @Override
    public JComponent getJComponent()
    {
        return label;
    }

}
