-- Main was becoming a little long, so here is separate lua that maintains all
-- of the enemies
minions = {};
tanks = {};
knights = {};
enemyProjectiles = {};

-- initializes enemies
function initEnemies()
   makeMinions();
   makeTanks();
   makeKnights();
   queen = Queen();
end

-- creates 9 minions
function makeMinions()
   local x = 20;
   local y = 160;
   for i = 1,5 do
      table.insert(minions, Minion(x, y));
      x = x + 80;
   end
   x = 60;
   y = 220;
   for i = 1,4 do
      table.insert(minions, Minion(x, y));
      x = x + 80;
   end
end

-- creates 3 tanks
function makeTanks()
   local x = 0;
   local y = 100;
   for i = 1,3 do
      table.insert(tanks, Tank(x, y));
      x = x + 160;
   end
end

-- creates 2 knights, one with a special behavior (loyal)
function makeKnights()
   table.insert(knights, Knight(100, 105));
   table.insert(knights, LoyalKnight(260, 105));
end

-- update the army
function updateArmy(dt)
   -- update minions
   for i = 1, #minions do
      minions[i]:update(dt);
   end
   
   -- update tanks
   for i = 1, #tanks do
      tanks[i]:update(dt);
   end
   
   -- update knights
   for i = 1, #knights do
      knights[i]:update(dt);
   end
   
   -- update queen
   queen:update(dt);
   
   -- update enemy projectiles
   for i = 1, #enemyProjectiles do
      enemyProjectiles[i]:update(dt);
   end
end

-- draw the army
function drawArmy()
   -- draw minions
   for i = 1, #minions do
      if minions[i].dead == false then
         minions[i]:draw();
      end
   end
   
   -- draw tanks
   for i = 1, #tanks do
      if tanks[i].dead == false then
         tanks[i]:draw();
      end
   end
   
   -- draw knights
   for i = 1, #knights do
      if knights[i].dead == false then
         knights[i]:draw();
      end
   end
   
   -- draw queen
   queen:draw();
   
   -- draw enemy projectiles
   for i = 1, #enemyProjectiles do
      enemyProjectiles[i]:draw();
   end
end

-- checks each enemy's interaction with player projectiles
function checkArmyCollision()
   for j = 1, #playerProjectiles do
      for i = 1, #minions do
         if minions[i].dead == false then
            minions[i]:checkProjectileCollision(playerProjectiles[j]);
         end
      end
      for i = 1, #tanks do
         if tanks[i].dead == false then
            tanks[i]:checkProjectileCollision(playerProjectiles[j]);
         end
      end
      for i = 1, #knights do
         if knights[i].dead == false then
            knights[i]:checkProjectileCollision(playerProjectiles[j]);
         end
      end
      if queen.asleep == false then
         queen:checkProjectileCollision(playerProjectiles[j]);
      end
   end
end

-- cleans up the enemy projectiles because we don't want to be holding onto them
-- indefinitely...
function cleanArmy()
   for i,ep in ipairs(enemyProjectiles) do
      if ep.dead then
         table.remove(enemyProjectiles, i);
      end
   end
end