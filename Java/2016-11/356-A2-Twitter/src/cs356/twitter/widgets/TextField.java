/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.widgets;

//-----------------------------------imports------------------------------------
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * This method is a simple Button. It's really just a JButton but made so that a
 * JTextField can be created in a more compact way and added to a JFrames JPanel
 * more easily.
 */
public class TextField implements Widget
{
//-----------------------------------field--------------------------------------
    /**
     * The JTextField
     */
    private JTextField textField;
    
//---------------------------------constructor----------------------------------
    /**
     * This constructor initializes the {@link #textField} as needed in one
     * declaration
     * @param name
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public TextField(String name,
            int x,
            int y,
            int width,
            int height)
    {
        textField = new JTextField(name);
        textField.setBounds(x, y, width, height);
    }
    
//-----------------------------------methods------------------------------------
    /**
     * This is a sort of QOL method. It exists because I got tired of
     * manually clearing a JTextField after successfully inputting a string.
     */
    public void clear()
    {
        textField.setText("");
    }
    
    /**
     * This simply returns the {@link #textField}
     */
    @Override
    public JComponent getJComponent()
    {
        return textField;
    }
    
    /**
     * This method simply returns the text in the {@link #textField}.
     */
    public String getTFText()
    {
        return textField.getText();
    }
}
