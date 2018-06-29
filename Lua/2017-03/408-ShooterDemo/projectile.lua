-- what we shoot with
Projectile = Object:extend();

function Projectile:new(x, y, speed, damageModifier, color, width, height)
   -- how to cheat using defaults
   self.width = width or 6;
   self.height = height or 8;
   
   self.x = x - self.width/2;
   self.y = y;
   self.damage = 2*damageModifier;
   self.speed = speed;
   self.dead = false;
   self.color = color;
end

-- draw
function Projectile:draw()
   love.graphics.setColor(self.color);
   love.graphics.rectangle("fill", self.x, self.y, self.width, self.height);
end

-- update
function Projectile:update(dt)
   self.y = self.y + self.speed * dt;
   
   -- mark ourselves dead when we leave the window so we can be cleaned up
   if (checkYBounds == false) then
      self.dead = true;
   end
end

-- checks the y bounds
function checkYBounds()
   if (self.y < 0) or
   (self.y > WINDOW_HEIGHT) then
      return false;
   end
   
   return true;
end