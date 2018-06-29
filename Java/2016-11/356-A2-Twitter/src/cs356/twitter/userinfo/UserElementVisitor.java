/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.userinfo;

//-----------------------------------imports------------------------------------
import java.util.List;

/**
 * This interface is for the visitor for {@link UserElement}s.
 */
public interface UserElementVisitor
{
//-----------------------------------methods------------------------------------
    /**
     * For visiting a {@link User}
     * @param user
     */
    public void visitUser(User user);
    
    /**
     * For visiting a {@link UserGroup}
     * @param group
     */
    public void visitGroup(UserGroup group);
    
    /**
     * For visiting {@link User}s' messages.
     * @param messages
     */
    public void visitMessage(List<String> messages);
    
    /**
     * For visiting {@link User}s' messages as well, but seeking positive
     * messages.
     * @param messages
     */
    public void visitPosMessage(List<String> messages);
}
