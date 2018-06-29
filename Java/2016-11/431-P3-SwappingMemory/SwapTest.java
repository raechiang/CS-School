/**
 * Rachel Chiang
 * CS 431-02
 * Project 3 Swapping
 */
public class SwapTest
{
    public static void main(String[] args)
    {
        UserInterface ui = new UserInterface();
        MemoryManager memManager = new MemoryManager();
        boolean stop = false;
        
        String command = "";
        while (!(stop))
        {
            command = ui.getUserCommand();
            if ((ui.wantsToStop(command)))
            {
                stop = true;
            }
            else
            {
                if (!(memManager.isValid(command)))
                {
                    ui.printBadInputMessage();
                }
                else
                {
                    if (!(memManager.processCommand()))
                    {
                        ui.printBadInputMessage();
                    }
                }
            }
        }
        
        ui.printExitMessage();
    }
}
