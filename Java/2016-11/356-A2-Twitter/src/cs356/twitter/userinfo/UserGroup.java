/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.userinfo;

/**
 * This method simply represents "directories" of {@link User}s.
 */
public class UserGroup implements UserElement
{
//-----------------------------------fields-------------------------------------
    /**
     * This is the name of the group, instantiated by {@link #UserGroup(String)}.
     */
    private String groupName;
    
//---------------------------------constructor----------------------------------
    /**
     * This is the constructor. It just makes the {@link #groupName}.
     * @param name
     */
    public UserGroup(String name)
    {
        this.groupName = name;
    }
    
//-----------------------------------methods------------------------------------
    /**
     * This is a simple getter for {@link #groupName} disguised as an {@link 
     * #toString()}.
     */
    @Override
    public String toString()
    {
        return groupName;
    }
    
    /**
     * This accepts a visitor.
     */
    @Override
    public void accept(UserElementVisitor visitor)
    {
        visitor.visitGroup(this);
    }
}
