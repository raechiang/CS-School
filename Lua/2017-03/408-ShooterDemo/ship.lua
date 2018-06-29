-- The Ship class where most of the "classes" inherit from
Ship = Object:extend();

-- constructor
function Ship:new(x, y, width, height, speed, health, damageModifier)
   self.x = x;
   self.y = y;
   self.width = width;
   self.height = height;
   self.speed = speed;
   self.health = health;
   self.damageModifier = damageModifier;
   self.dead = false;
end

-- checks to make sure the ship is within window x bounds
function Ship:checkXBounds(position)
   if (position <= 0) then
      return false;
   elseif (position + self.width >= WINDOW_WIDTH) then
      return false;
   end
   
   return true;
end

-- checks if this ship has collided with object (a projectile)
function Ship:checkProjectileCollision(obj)
   local s_left = self.x;
   local s_right = self.x + self.width;
   local s_top = self.y;
   local s_bot = self.y + self.height;
   
   local obj_left = obj.x;
   local obj_right = obj.x + obj.width;
   local obj_top = obj.y;
   local obj_bot = obj.y + obj.height;
   
   if (s_right > obj_left) and
   (s_left < obj_right) and
   (s_bot > obj_top) and
   (s_top < obj_bot) then
      self:takeDamage(obj.damage);
      obj.dead = true;
      return true;
   end
   return false;
end

-- The ship takes damage if it collided with a projectile
function Ship:takeDamage(damageTaken)
   self.health = self.health - damageTaken;
   if (self.health <= 0) then
      self.dead = true;
   end
end