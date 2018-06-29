-- Just keeps the UI stuff over here. I think this could afford to be bigger and
-- the main could afford to be neater...
deathImg = love.graphics.newImage("urdead.png"); -- death image :(
winImg = love.graphics.newImage("uwin.png"); -- win image :)

-- Draws the interface, which at the moment is only a game over image, a
-- "status" bar, and the player's health. In the future it could be fun to add
-- some taunting remarks or fluffy story
function drawInterface()
   if gameOver then
      if youWin then
         love.graphics.setColor(255, 255, 255);
         love.graphics.draw(winImg, 50, 300);
      else
         love.graphics.setColor(255, 255, 255);
         love.graphics.draw(deathImg, 34, 300);
      end
   end
   
   love.graphics.setColor(255, 255, 255);
   love.graphics.rectangle("fill", 0, 650, WINDOW_WIDTH, 100); -- x, y, width, height
   
   love.graphics.setColor(255, 0, 0);
   love.graphics.print(pHealth, 5, 660, 0, 2, 2);
end

-- player's health
function updateInterface()
   pHealth = player.health .. "/" .. player.maxHealth;
end