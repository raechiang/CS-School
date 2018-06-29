-- A gross, messy main function! (I'm sorry)
function love.load()
   -- This is how Lua opens other local files
   require("globals");
   Object = require "classic";
   require "interface";
   require "ship";
   require "player";
   require "redplayer";
   require "enemy";
   require "minion";
   require "tank";
   require "knight";
   require "loyalknight";
   require "queen";
   require("army");
   require "projectile";
   require "powerUp";
   require "heal";
   
   gameOver = false;
   youWin = true;
   
   player = RedPlayer(); -- sets initial pos of player x, y
   playerProjectiles = {};
   powerUp = nil;
   
   initEnemies(); -- sets up the enemies from army.lua
end

-- Updates the game
function love.update(dt)
   if queen.dead then
      gameOver = true;
   end
   if gameOver == true then
      -- no more updating
   else
      -- update the player
      player:update(dt);
      -- and their projectiles
      for i = 1, #playerProjectiles do
         playerProjectiles[i]:update(dt);
      end
      
      -- attempt to spawn the powerUp if it hasn't already been spawned
      if (powerUp == nil) then
         spawnPowerUp();
      end
      
      -- update the enemies
      updateArmy(dt);
      
      -- check the player's interaction
      shipObjectInteraction();
      
      -- clean up the player's mess
      cleanUp();
   end
   
   -- update the ui
   updateInterface();
end

-- draws everything
function love.draw()
   -- draw the player
   player:draw();
   -- and their projectiles
   for i = 1, #playerProjectiles do
      playerProjectiles[i]:draw();
   end
   
   -- draw the enemies
   drawArmy();
   
   -- draw the interface
   drawInterface();
   
   -- if we spawned the powerUp then lets draw it
   if (powerUp) then
      powerUp:draw();
   end
end

-- if a key is pressed, let the player take action
function love.keypressed(key)
   player:keypressed(key);
end

-- clean up the game and clean up the player's projectiles because we want Lua
-- to eventually gc them
function cleanUp()
   if player.dead then
      gameOver = true;
      youWin = false;
   end
   
   for i,pp in ipairs(playerProjectiles) do
      if pp.dead then
         table.remove(playerProjectiles, i);
      end
   end
   
   cleanArmy();
end

-- checks interactions
function shipObjectInteraction()
   -- player+item interaction?
   if (powerUp) then
      if (player:checkItemCollision(powerUp)) then
         powerUp = nil;
      end
   end
   
   -- enemies' interactions
   checkArmyCollision();
   
   -- player+enemyProjectiles interaction?
   for i = 1, #enemyProjectiles do
      player:checkProjectileCollision(enemyProjectiles[i]);
   end
end

-- spawns Heal if you have 2 health ;)
function spawnPowerUp()
   if (player.health < 3) then
      powerUp = Heal(player.x);
   end
end