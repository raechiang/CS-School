# Tic-Tac-Toe
This project involves passing (Serializable) messages back and forth between the server to play a game of Tic-Tac-Toe. The required steps are as follows:
<ol>
<li>Establish a socket to codebank.xyz on port 38006.</li>
<li>Send a ConnectMessage identifying yourself.</li>
<li>Send a CommandMessage to start a new game with the server.</li>
<li>The server will respond with a BoardMessage showing the starting board configuration. </li>
<li>Send a MoveMessage indicating where you are making your move.</li>
<li>The server will move and reply with another BoardMessage.</li>
</ol>
(Steps 5 and 6 repeat until the game ends)
