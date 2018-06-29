-- You. Why are you destroying things?
Player = Ship:extend()

-- create a new player
function Player:new(speed, health, damageModifier)
   -- super x, y, width, height, speed, health, damageModifier
   Player.super.new(self, PLAYER_X0, PLAYER_Y0, PLAYER_WIDTH, PLAYER_HEIGHT, speed, health, damageModifier);
   self.maxHealth = health;
   self.pSpeed = -600;
end

-- You can only move left/right
function Player:update(dt)
   if (love.keyboard.isDown("left")) then
      self.x = self.x - self.speed * dt;
      if (self:checkXBounds(self.x) == false) then
         self.x = 0;
      end
   elseif (love.keyboard.isDown("right")) then
      self.x = self.x + self.speed * dt;
      if (self:checkXBounds(self.x) == false) then
         self.x = WINDOW_WIDTH - self.width;
      end
   end
end

-- space = shoot
function Player:keypressed(key)
   if (key == "space") then
      table.insert(playerProjectiles, Projectile(self.x + self.width/2, self.y, self.pSpeed, self.damageModifier, self.color));
   end
end

-- Player checks to see if they picked up an item
function Player:checkItemCollision(item)
   local s_left = self.x;
   local s_right = self.x + self.width;
   local s_top = self.y;
   local s_bot = self.y + self.height;
   
   local i_left = item.x;
   local i_right = item.x + item.width;
   local i_top = item.y;
   local i_bot = item.y + item.height;
   
   if (s_right > i_left) and
   (s_left < i_right) and
   (s_bot > i_top) and
   (s_top < i_bot) then
      self:useItem(item);
      item.dead = true;
      return true;
   end
   return false;
end

-- The player uses the item
function Player:useItem(item)
   if item:is(Heal) then
      self.health = self.maxHealth;
   end
end