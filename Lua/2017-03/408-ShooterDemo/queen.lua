-- Our beloved queen (the boss). The point of the game is to kill her. You have
-- to wake her up first though.
Queen = Enemy:extend();

function Queen:new()
   -- super x, y, width, height, speed, health, cooldown, damageModifier
   Queen.super.new(self, QUEEN_X0, QUEEN_Y0, QUEEN_WIDTH, QUEEN_HEIGHT, QUEEN_SPEED, QUEEN_HEALTH, QUEEN_CD, QUEEN_DMG_MOD);
   self.color = {255, 245, 112};
   self.pSpeed = 600;
   -- She starts out asleep. Weird, huh? Just seemed like it wouldn't be very
   -- interesting if you could just shoot the queen so easily since the enemies'
   -- AI is garbage
   self.asleep = true;
end

-- update
function Queen:update(dt)
   if (self.dead == false) then
      -- if she is not dead
      if (self.asleep) then
         local deadTanks = 0;
         for i=1, #tanks do
            if tanks[i].dead then
               deadTanks = deadTanks + 1;
            end
         end
         
         if deadTanks == 3 then
            self:awaken(); -- she wakes up!
         end
      else
         self:move(dt);
         self:attack(dt);
      end
   end
end

-- draw
function Queen:draw()
   if (self.asleep) then
      love.graphics.setColor(109, 80, 0);
   else
      love.graphics.setColor(self.color);
   end
   love.graphics.polygon("fill", self:establishVertices());
end

-- she shoots!
function Queen:shoot(pSpeed)
   -- x, y, speed, damage, color, width, height
   table.insert(enemyProjectiles, Projectile(self.x + self.width/2, self.y, self.pSpeed, self.damageModifier, self.color, 20, 100));
end

-- Gasp! The dead awaken to fight once again for their queen
function Queen:awaken()
   self.asleep = false;
   -- revive minions
   for i = 1, #minions do
      if (minions[i].dead) then
         minions[i]:respawn();
         minions[i].color = self.color;
      end
   end
   
   -- revive tanks
   for i = 1, #tanks do
      if (tanks[i].dead) then
         tanks[i]:respawn();
         tanks[i].color = self.color;
      end
   end
   
   -- revive knights
   for i = 1, #knights do
      if (knights[i].dead) then
         knights[i]:respawn();
         knights[i].color = self.color;
      end
   end
end

-- The queen's sporadic(?) movement
function Queen:move(dt)
   -- she copies you, sort of. She moves in the opposite direction
   if love.keyboard.isDown("right") then
      self.x = self.x - self.speed * dt;
      if (self:checkXBounds(self.x) == false) then
         self.x = 0;
      end
   elseif love.keyboard.isDown("left") then
      self.x = self.x + self.speed*4 * dt;
      if (self:checkXBounds(self.x) == false) then
         self.x = WINDOW_WIDTH - self.width;
      end
   else
      -- she has a chance to sense approaching projectiles
      if (math.random() < 0.3) then
         self:sense();
      end
      
      -- she also has a chance to teleport back to the middle, especially if she
      -- is at the boundary
      if (math.random() < 0.1) then
         if (math.random() <= 0.5) then
            self.x = self.x - self.speed * dt;
            if (self:checkXBounds(self.x) == false) then
               if (math.random() < 0.6) then
                  self:teleport();
               else
                  self.x = 0;
               end
            end
         else
            self.x = self.x + self.speed*4 * dt;
            if (self:checkXBounds(self.x) == false) then
               if (math.random() < 0.6) then
                  self:teleport();
               else
                  self.x = WINDOW_WIDTH - self.width;
               end
            end
         end
      end
   end
end

-- she was very annoying to draw
function Queen:establishVertices()
   local v = { -- x1,y1, x2,y2, ...
      (self.x + self.width/4), self.y,
      self.x, (self.y + self.height/2),
      (self.x + self.width/4), (self.y + self.height),
      (self.x + 3*(self.width/4)), (self.y + self.height),
      (self.x + self.width), (self.y + self.height/2),
      (self.x + 3*(self.width/4)), self.y
   };
   return v;
end

-- she's not very smart so she has to TP sometimes
-- if the player moves in one direction too much she "stays"
-- on one side of the window, making it very easy to kill her
function Queen:teleport()
   self.x = QUEEN_X0;
   self.y = QUEEN_Y0;
end

-- This would backfire on her if you're shooting in the center anyway
function Queen:sense()
   local yVision = 200 + self.height + self.y;
   for i = 1, #playerProjectiles do
      if (playerProjectiles[i].y <= yVision) then
         if (math.random() < 0.1) then
            self:teleport();
         end
      end
   end
end