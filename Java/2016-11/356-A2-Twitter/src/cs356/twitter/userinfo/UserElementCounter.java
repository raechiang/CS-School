/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.userinfo;

//-----------------------------------imports------------------------------------
import java.util.Arrays;
import java.util.List;

/**
 * This class is a visitor for the {@link UserElement}s. All of the analytics
 * that we were required to do are really just counters, though the positive
 * message percentage requires a special condition before counting.
 */
public class UserElementCounter implements UserElementVisitor
{
//-----------------------------------fields-------------------------------------
    /**
     * This field is for {@link #visitUser(User)} and is retrieved through 
     * {@link #getUsersCounter()}. It simply counts the total number of users.
     */
    private int usersCounter;
    
    /**
     * This field is for {@link #visitGroup(UserGroup)} and is retrieved through
     * {@link #getGroupsCounter()}. It simply counts the total number of groups,
     * EXCLUDING the default ROOT group. I was not entirely sure if we were 
     * supposed to include the root, but I chose not to. This can be easily
     * changed in the {@link UsersTree} class's accept(UserElementVisitor)
     * method by removing the getNextNode().
     */
    private int groupsCounter;
    
    /**
     * This field is for {@link #visitMessage(List)} and is retrieved through
     * {@link #getMessagesCounter()}. It simply counts the total number of
     * tweets.
     */
    private int messagesCounter;
    
    /**
     * This field is for {@link #visitPosMessage(List)} and the percentage that
     * it creates with the {@link #messagesCounter} is what is returned by
     * {@link #getPositivePercentage()}. It is a double because percentage
     * calculations are a floating point calculation.
     */
    private double positiveMessagesCounter;
    
    /**
     * This field is for marking whether a message is positive or not.
     */
    private List<String> positiveWords;
    
//---------------------------------constructor----------------------------------
    /**
     * This is the constructor. It starts all the counters at 0 and calls {@link
     *  #populatePositiveList()} to make {@link #positiveWords}.
     */
    public UserElementCounter()
    {
        usersCounter = 0;
        groupsCounter = 0;
        messagesCounter = 0;
        positiveMessagesCounter = 0;
        populatePositiveList();
    }
    
//-----------------------------------methods------------------------------------
    /**
     * This method is for overriding the methods from the interface {@link 
     * UserElementVisitor}. It handles visiting {@link User}s.
     */
    @Override
    public void visitUser(User user)
    {
        ++usersCounter;
    }
    
    /**
     * This method is for overriding the methods from the interface {@link
     * UserElementVisitor}. It handles visiting {@link UserGroup}s.
     */
    @Override
    public void visitGroup(UserGroup group)
    {
        ++groupsCounter;
    }
    
    /**
     * This method is for overriding the methods from the interface {@link
     * UserElementVisitor}. It handles visiting the {@link User}s' messages.
     */
    @Override
    public void visitMessage(List<String> messages)
    {
        for (String m : messages)
        {
            ++messagesCounter;
        }
    }
    
    /**
     * This method is for overriding the methods from the interface {@link
     * UserElementVisitor}. It handles visiting the {@link User}s' messages
     * and must take the extra step of seeing if the message is positive. To
     * check if it's positive, it uses {@link #isPositive(String)} for help.
     */
    @Override
    public void visitPosMessage(List<String> messages)
    {
        for (String m : messages)
        {
            if (isPositive(m))
            {
                ++positiveMessagesCounter;
            }
        }
    }
    
    /**
     * This method just returns true or false if a passed String message has at
     * least one positive word. The words to seek are in {@link #positiveWords},
     * which were instantiated earlier with {@link #populatePositiveList()}.
     */
    private boolean isPositive(String message)
    {
        for (String w : positiveWords)
        {
            if (message.contains(w))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * This simply adds a bunch of "positive" words to {@link #positiveWords}.
     * These words are looked for in the users' messages, so the message can be
     * counted as positive in {@link #visitPosMessage(List)}.
     */
    private void populatePositiveList()
    {
        String[] niceWords = {"good", "great", "excellent", "fantastic", ":D",
                ":)", ":3", ":]", "happy", "fine", "fabulous", "fun",
                "delicious", "beautiful", "lovely", "awesome", "Good", "Great",
                "Excellent", "Fantastic", "Happy", "Fine", "Fabulous", "Fun",
                "Delicious", "Beautiful", "Lovely", "Awesome"};
        positiveWords = Arrays.asList(niceWords);
    }
    
    /**
     * This method simply returns {@link #usersCounter}.
     */
    public int getUsersCounter()
    {
        return usersCounter;
    }
    
    /**
     * This method simply returns {@link #groupsCounter}.
     */
    public int getGroupsCounter()
    {
        return groupsCounter;
    }
    
    /**
     * This method simply returns {@link #messagesCounter}.
     */
    public int getMessagesCounter()
    {
        return messagesCounter;
    }
    
    /**
     * This method simply returns the percentage of positive messages ({@link 
     * #positiveMessagesCounter}) out of total messages ({@link 
     * #messagesCounter}).
     */
    public double getPositivePercentage()
    {
        if (messagesCounter == 0)
        {
            // Can't divide by zero :O
            return 0;
        }
        return ((positiveMessagesCounter / messagesCounter) * 100);
    }
}
