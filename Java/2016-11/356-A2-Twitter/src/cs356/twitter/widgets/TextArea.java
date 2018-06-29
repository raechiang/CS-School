/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.widgets;

//-----------------------------------imports------------------------------------
import javax.swing.JComponent;
import javax.swing.JTextArea;

/**
 * This method is a simple Button. It's really just a JButton but made so that a
 * JTextArea can be created in a more compact way and added to a JFrames JPanel
 * more easily.
 */
public class TextArea implements Widget
{
//-----------------------------------fields-------------------------------------
    /**
     * This is the {@link #textArea}.
     */
    private JTextArea textArea;
    
    /**
     * This is so that when the text is displayed it will display the whole 
     * history of the {@link #textArea} strings.
     */
    private String text;
    
//---------------------------------constructor----------------------------------
    /**
     * This is the constructor which instantiates the {@lnk #textArea} and the
     * {@link #text}.
     * @param text
     * @param editable
     */
    public TextArea(String text, boolean editable)
    {
        textArea = new JTextArea();
        this.text = text + "\n";
        textArea.setText(this.text);
        textArea.setEditable(false); // don't want the user to touch these since
        // the textArea in this assignment is only used to display messages for
        // the admin to understand more clearly what is going on and to see the
        // statistics found by the visitors.
    }
    
//-----------------------------------methods------------------------------------
    /**
     * This is to add new text to the {@link #text} which is displayed via the
     * {@link #textArea}.
     * @param text
     */
    public void addText(String text)
    {
        this.text += text + "\n";
        textArea.setText(this.text);
    }
    
    /**
     * This method simply returns the {@link #textArea}
     */
    @Override
    public JComponent getJComponent()
    {
        return textArea;
    }

}
