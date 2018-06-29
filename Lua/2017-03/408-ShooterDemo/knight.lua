-- A specialized enemy that is mobile
Knight = Enemy:extend();

-- I actually don't like how I wrote the Ships but it's too late to turn back :c
function Knight:new(x, y)
   -- super x, y, width, height, speed, health, cooldown, damageModifier
   Knight.super.new(self, x, y, KNIGHT_WIDTH, KNIGHT_HEIGHT, KNIGHT_SPEED, KNIGHT_HEALTH, KNIGHT_CD, KNIGHT_DMG_MOD);
   -- a weird dark green color
   self.color = {0, 132, 11};
   -- the speed (and direction) of the projectile... I suppose more accurately,
   -- the velocity.
   self.pSpeed = 600;
   self.positiveDirection = false;
end

-- the update
function Knight:update(dt)
   if (self.dead == false) then
      self:attack(dt);
      self:move(dt);
   end
end

-- draws the knight. as you can see, they are triangles
function Knight:draw()
   love.graphics.setColor(self.color);
   local vertices = {
      self.x + self.width/2, self.y,
      self.x, self.y + self.height,
      self.x + self.width, self.y + self.height
   };
   love.graphics.polygon("fill", vertices);
end

-- moves the knight. By default, they fly left or right until they hit the 
-- window boundary
function Knight:move(dt)
   if positiveDirection then
      self.x = self.x + self.speed * dt;
      if (self:checkXBounds(self.x) == false) then
         self.x = WINDOW_WIDTH - self.width;
         positiveDirection = false;
      end
   else
      self.x = self.x - self.speed * dt;
      if (self:checkXBounds(self.x) == false) then
         self.x = 0;
         positiveDirection = true;
      end
   end
end

-- Gasp! They didn't actually die! The sight of their beloved queen awakening
-- fills them with d e t e r m i n a t i o n . . .
function Knight:respawn()
   self.health = KNIGHT_HEALTH + 2;
   self.dead = false;
   self.cooldown = KNIGHT_CD - 1;
end