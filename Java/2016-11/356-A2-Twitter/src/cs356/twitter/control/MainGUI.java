/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.control;

//-----------------------------------import-------------------------------------
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import cs356.twitter.widgets.*;
import cs356.twitter.userinfo.*;
 
/**
 * This class is the centralized admin control panel, and as such it implements
 * the singleton design pattern. This class creates the main GUI window and has
 * multiple functions beyond that, as follows:
 *     - Adding a new user
 *     - Making a new group (directory)
 *     - Opening the user view for a selected user
 *     - Visiting and analyzing four pieces of data:
 *         1. Finding the total number of users
 *         2. Finding the total number of groups
 *         3. Finding the total number of messages
 *         4. Finding the percentage of positive messages
 */
public class MainGUI implements ActionListener
    {
//-----------------------------------fields-------------------------------------
    /**
     * One of the core aspects of a singleton design pattern: the protected
     * static instance, which is used in {@link TwitterApp} with the getter 
     * method {@link #getInstance()} below.
     */
    protected static MainGUI instance;
    
    /**
     * The following fields are for the GUI.
     */
    
    /**
     * This field is the main admin window and is instantiated and used only 
     * once (and in the constructor below {@link #MainGUI()}).
     */
    private JFrame mainframe;
    
    /**
     * This field is the content pane for the main window.
     */
    private JPanel contentPane;
    
    /**
     * This field contains the users and groups. See {@link UsersTree} for more 
     * information.
     */
    private UsersTree treePanel;
    
    /**
     * This field is for the admin to input a string for a new user. It is
     * instantiated in {@link #makeWidgets()} and is used in {@link #addUser}. 
     */
    private TextField addUserTextField;
    
    /**
     * This field is for the admin to input a string for a new group. It is
     * instantiated in {@link #makeWidgets()} and is used in {@link #addGroup}.
     */
    private TextField addGroupTextField;
    
    /**
     * This field is used to display the statistics found by the visitors as
     * well as the error messages and other little details.
     */
    private TextArea statsTextArea;
    
    /**
     * This method holds a list of the widgets and demonstrates the composite
     * design pattern.
     */
    private List<Widget> widgets;
    
    /**
     * This is a sort of QOL field. It did not look pretty and wasn't very 
     * effective to have a bunch of windows open right on top of each other.
     */
    private int xyOffset;
    
    
//---------------------------------constructor----------------------------------
    /**
     * The constructor itself initializes {@link #xyOffset}, {@link #mainframe},
     * {@link #contentPane}, {@link #treePanel}, {@link #statsTextArea}, and
     * {@link #widgets}. It uses {@link #makeWidgets()} to help create the rest
     * of the GUI elements.
     */
    private MainGUI()
    {
        // offset is initialized to 10
        xyOffset = 10;
        
        // creation and configuration of the JFrame mainframe and JPanel
        // contentPane
        mainframe = new JFrame("Twitter (Admin)");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setBounds(100, 100, 700, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainframe.setContentPane(contentPane);
        contentPane.setLayout(null);
        mainframe.setVisible(true);
        
        // The other widgets that will be added to contentPane or their
        // corresponding ScrollPanes
        treePanel = new UsersTree();
        statsTextArea = new TextArea("Greetings!", false);
        widgets = new LinkedList<Widget>();
        makeWidgets();
        
        for (Widget w : widgets)
        {
            contentPane.add(w.getJComponent());
        }
    }
    
//-----------------------------------methods------------------------------------
    /**
     * Abiding by singleton pattern rules: to access this MainGUI class outside
     * of itself is only through this method.
     */
    public static MainGUI getInstance()
    {
        if (instance == null)
        {
            instance = new MainGUI();
        }
        return instance;
    }
    
    /**
     * This method helps {@link #MainGUI()} create the widgets that fill the
     * window. It looks somewhat more compact like this, I think. I did not
     * particularly like the look and feel of four to six lines to initialize
     * all of the widgets. Like the composite design pattern demonstrated in
     * class.
     */
    private void makeWidgets()
    {
        // widgets that affect the tree or is the tree itself
        widgets.add(new ScrollPane(10, 11, 300, 289, treePanel.getTree()));
        widgets.add(new Button("Add User", "add user", 
                        this, 560, 10, 114, 30));
        widgets.add(new Button("Make Group", "make group", 
                        this, 560, 51, 114, 30));
        addUserTextField = new TextField("(new username)", 
                        320, 10, 230, 30);
        widgets.add(addUserTextField);
        addGroupTextField = new TextField("(new group name)", 
                        320, 51, 230, 30);
        widgets.add(addGroupTextField);
        
        // widget to open the user view
        widgets.add(new Button("Open User View", "open user", 
                        this, 320, 188, 354, 30));
        
        // widgets that display the statistics
        widgets.add(new ScrollPane(320, 92, 354, 85, 
                        statsTextArea.getJComponent()));
        widgets.add(new Button("User Total", "show users", 
                        this, 320, 229, 170, 30));
        widgets.add(new Button("Group Total", "show groups", 
                        this, 505, 229, 169, 30));
        widgets.add(new Button("Messages Total", "show messages", 
                        this, 320, 270, 170, 30));
        widgets.add(new Button("Positive Percentage", "show percentage", 
                        this, 505, 270, 170, 30));
    }
    
    /**
     * This method handles the actions.
     *     - add user: Adds a user with username given by 
     *         {@link #addUserTextField}. Uses {@link #addUser()} for help.
     *     - make group: Makes a new group with group name given by
     *         {@link #addGroupTextField}. Uses {@link #makeGroup()} for help.
     *     - open user: Opens the user view GUI. Uses {@link #openUserView()}
     *         for help. See {@link UserGUI} for more information.
     *     - show users: Finds the total number of users in the tree using
     *         {@link #makeVisitor()} and then displays the result to 
     *         {@link #statsTextArea}.
     *     - show groups: Finds the total number of groups in the tree 
     *         EXCLUDING the root using {@link #makeVisitor()} and then
     *         displays the result to {@link #statsTextArea}.
     *     - show messages: Finds the total number of messages across all users
     *         using {@link #makeVisitor()} and then displays the result to
     *         {@link #statsTextArea}.
     *     - show percentage: Finds the percentage of messages with some 
     *         positive key words using {@link #makeVisitor()} and then
     *         displays the result to {@link #statsTextArea}.
     */
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        
        if (command.equals("add user"))
        {
            // Adds new user
            addUser();
        }
        else if (command.equals("make group"))
        {
            // Makes new group
            makeGroup();
        }
        else if (command.equals("open user"))
        {
            // opens existing user's view
            openUserView();
        }
        else if (command.equals("show users"))
        {
            // visits and analyzes nodes for total number of users
            statsTextArea.addText("Total Number of Users: " + makeVisitor().getUsersCounter());
        }
        else if (command.equals("show groups"))
        {
            // visits and analyzes nodes for total number of groups
            statsTextArea.addText("Total Number of Groups: " + makeVisitor().getGroupsCounter());
        }
        else if (command.equals("show messages"))
        {
            // visits and analyzes nodes for total number of messages
            statsTextArea.addText("Total Number of Messages: " + makeVisitor().getMessagesCounter());
        }
        else if (command.equals("show percentage"))
        {
            // visits and analyzes nodes for the positivity percentage
            statsTextArea.addText("Message Positivity: " + (Math.round(makeVisitor().getPositivePercentage())) + "%");
        }
    }
    
    /**
     * This method is used by {@link #actionPerformed(ActionEvent)} to look
     * a little cleaner. It adds a new user with the username given by
     * {@link #addUserTextField} as long as the username is unique and the
     * selected node is not a user itself (since only groups can have
     * children). See {@link UsersTree} for more details.
     */
    private void addUser()
    {
        String name = ((JTextField) addUserTextField.getJComponent()).getText();
        // checks if empty
        if (!(name.equals("")))
        {
            // a successful addition will return the added node
            if (treePanel.addObject(new User(name)) != null)
            {
                // so for QOL we can clear the text field :D
                addUserTextField.clear();
            }
            else
            {
                // Using text area to display an error message
                statsTextArea.addText("ERROR:\tUsername must be unique." +
                                    "\n\tOnly groups may have children.");
            }
        }
        else
        {
            // if it's empty, that means no username was given but we clicked
            // the button anyway...
            statsTextArea.addText("ERROR: No username given.");
        }
    }
    
    /**
     * This method is used by {@link #actionPerformed(ActionEvent)} to look a
     * little bit cleaner. It works quit similarly to {@link #addUser()}.
     */
    private void makeGroup()
    {
        String name =
                ((JTextField) addGroupTextField.getJComponent()).getText();
        // check if the text field is empty
        if (!(name.equals("")))
        {
            if (treePanel.addObject(new UserGroup(name)) != null)
            {
                // if the group was successfully added, clear the text field
                addGroupTextField.clear();
            }
            else
            {
                // error message
                statsTextArea.addText("ERROR: Group name must be unique.");
            }
        }
        else
        {
            // error message
            statsTextArea.addText("ERROR: No group name given.");
        }
    }
    
    /**
     * This method is used by {@link #actionPerformed(ActionEvent)} to look a
     * little bit cleaner. This method opens a new window for the selected
     * user.
     */
    private void openUserView()
    {
        DefaultMutableTreeNode selectedElement = treePanel.getSelected();
        if (selectedElement != null)
        {
            // check to see if there is no selected node
            if (selectedElement.getUserObject() instanceof User)
            {
                // only if it's a user do we want to open a window
                // simple notification that a new window is being opened
                statsTextArea.addText("Opening " + 
                            selectedElement.getUserObject().toString() + 
                            "'s user view.");
                // this is the actual new window
                UserGUI userView = 
                        new UserGUI((User) selectedElement.getUserObject(), 
                                    treePanel, xyOffset+=10, statsTextArea);
                // the QOL improvement becomes a !QOL improvement if it offsets
                // too much.
                if (xyOffset >= 170)
                {
                    xyOffset = 0;
                }
            }
        }
        else
        {
            // error message
            statsTextArea.addText("ERROR: A user has not been selected.");
        }
    }
    
    /**
     * This method is used by {@link #actionPerformed(ActionEvent)} for two
     * reasons: reduce redundancy (increase reusability) and to, of course,
     * make the actionPerformed method look a little bit nicer. 
     */
    private UserElementCounter makeVisitor()
    {
        UserElementCounter uec = new UserElementCounter();
        treePanel.accept(uec);
        return uec;
    }
}