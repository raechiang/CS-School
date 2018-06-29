/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.userinfo;

//-----------------------------------imports------------------------------------
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
 
/**
 * This class represents a structure that holds all the {@link UserElement}s
 * together. It is really only modifiable for adding new {@link UserElement}s,
 * though the elements themselves can be modified. Basically, once something is
 * added, it cannot be moved or removed.
 */
public class UsersTree extends JPanel
{
//-----------------------------------fields-------------------------------------
    /**
     * This field is the root of the tree which pretty much just gives access
     * to the top of the tree. Through this node, other nodes can be accessed
     * by navigating down the children. It is never changed, only accessed.
     */
    private final DefaultMutableTreeNode rootNode = 
            new DefaultMutableTreeNode(new UserGroup("(root)"));;
    
    /**
     * This field is the DefaultTreeModel. It is modifiable through adding new
     * nodes.
     */
    private DefaultTreeModel treeModel;
    
    /**
     * The tree is only read from (or navigated through) and is instantiated 
     * with the {@link #treeModel}.
     */
    private JTree tree;
    
//---------------------------------constructor----------------------------------
    /**
     * This is the constructor. It creates the new {@link #treeModel} using the
     * {@link #rootNode} which itself is a {@link UserGroup}. The tree is 
     * created with {@link #treeModel}.
     */
    public UsersTree()
    {
        treeModel = new DefaultTreeModel(rootNode);
        tree = new JTree(treeModel);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
    }
    
//-----------------------------------methods------------------------------------
    /**
     * This method adds a child to the currently selected node. It uses {@link
     * #getParentNode()} for help in finding the selected node and passes the
     * parentNode and the child (the object to be added) to {@link 
     * #addObject(DefaultMutableTreeNode,UserElement)}.
     */
    public DefaultMutableTreeNode addObject(UserElement child)
    {
        DefaultMutableTreeNode parentNode = getParentNode();
        return addObject(parentNode, child);
    }
    
    /**
     * This method checks to see if a {@link #UserElement} with the same name
     * already exists, and if it does, it will not add the new child. Otherwise,
     * it will continue with the adding procedure, adding to the {@link 
     * #treeModel} and making sure that when a new node is added, the newly 
     * added node is visible (the user does not have to scroll down if it's
     * added out of sight).
     * @param parent - The selected node to add the child to
     * @param child - The new {@link UserElement} to add to
     * @return - the newly added node is returned, but if it is null, then the
     *          addition was unsuccessful either because an entity of the same
     *          name already exists or because a new {@link UserElement} cannot
     *          be added to a {@link User}, only to a {@link UserGroup}.
     */
    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            UserElement child)
    {
        if (exists(child.toString()))
        {
            // if a UserElement of the same name exists, don't add the child
            return null;
        }
        
        // otherwise make a new node of the child and continue
        DefaultMutableTreeNode childNode = 
                new DefaultMutableTreeNode(child);

        if (parent == null)
        {
            // if no parent is selected, it will simply add to the root
            // directory
            parent = rootNode;
        }

        if (parent.getUserObject() instanceof UserGroup)
        {
            // the selected node is a UserGroup, so it's okay to add the child
            treeModel.insertNodeInto(childNode, parent, 
                    parent.getChildCount());

            tree.scrollPathToVisible(new TreePath(childNode.getPath()));

            return childNode;
        }
        else
        {
            // the selected node is not a UserGroup, so we can't add the child
            return null;
        }
    }
    
    /**
     * This method handles visiting every node in the tree, EXCLUDING the {@link
     * #rootNode}. It is up to the {@link UserElement} to handle the visit.
     * @param visitor
     */
    public void accept(UserElementVisitor visitor)
    {
        DefaultMutableTreeNode current = rootNode.getNextNode();
        while (current != null)
        {
            ((UserElement) current.getUserObject()).accept(visitor);
            current = current.getNextNode();
        }
    }
    
    /**
     * This simply returns the {@link #tree} to be displayed.
     * @return
     */
    public JTree getTree()
    {
        return tree;
    }
    
    /**
     * This simply gets the selected {@link User} and will not return a
     * {@link UserGroup}. It would probably be wise to rename this method.
     * @return - null if the selected node is a {@link UserGroup}, but it will
     *          return the selected node if it is a {@link User}.
     */
    public DefaultMutableTreeNode getSelected()
    {
        DefaultMutableTreeNode node = getParentNode();
        if (node.getUserObject() instanceof UserGroup)
        {
            return null;
        }
        else
        {
            return node;
        }
    }
    
    /**
     * This method simply gets the {@link UserElement} that has the same
     * username or group name as the passed argument.
     * @param name - the entity that is being sought after
     * @return - only null if it could not find the desired user
     */
    public UserElement getUser(String name)
    {
        DefaultMutableTreeNode current = rootNode;
        UserElement desired = null;
        while (current != null)
        {
            if (current.getUserObject().toString().equalsIgnoreCase(name))
            {
                desired = (UserElement) current.getUserObject();
            }
            current = current.getNextNode();
        }
        
        return desired;
    }
    
    /**
     * This method retrieves the selected node. It is used by {@link 
     * #addObject(UserElement)} and by {@link #getSelected()}.
     */
    private DefaultMutableTreeNode getParentNode()
    {
        TreePath parentPath = tree.getSelectionPath();
        
        DefaultMutableTreeNode parentNode = null;
        
 
        if (parentPath == null)
        {
            parentNode = rootNode;
        }
        else
        {
            parentNode = (DefaultMutableTreeNode)
                         (parentPath.getLastPathComponent());
        }
        return parentNode;
    }
    
    /**
     * This method simply checks to see if a {@link #UserElement} exists in 
     * the tree given a name.
     * @param name - the name of the object being sought
     * @return - true or false depending on whether it found the element or not
     */
    private boolean exists(String name)
    {
        if (getUser(name) == null)
        {
            return false;
        }
        return true;
    }
}
