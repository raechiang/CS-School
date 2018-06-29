-- The Knight, but better at protecting its beloved queen and therefore cooler
LoyalKnight = Knight:extend();

-- Loyal knights actually try guarding the queen
function LoyalKnight:new(x, y)
   -- super x, y
   LoyalKnight.super.new(self, x, y);
   self.guarding = false;
end

-- the knight will attempt to always stay in front of the queen
function LoyalKnight:move(dt)
   if self:guardQueen() then
      self.speed = 150;
   else
      self.speed = 350;
   end
   
   if (self.positiveDirection) then
      self.x = self.x + self.speed * dt;
      if (self.x + self.width > queen.x + queen.width) then
         self.x = queen.x + queen.width - self.width;
         self.positiveDirection = false;
      end
   else
      self.x = self.x - self.speed * dt;
      if (self.x < queen.x) then
         self.x = queen.x;
         self.positiveDirection = true;
      end
   end
end

-- update function
function LoyalKnight:update(dt)
   if (self.dead == false) then
      self:attack(dt);
      self:move(dt);
   end
end

-- that's a good boy :3
function LoyalKnight:guardQueen()
   if (self.x + self.width < queen.x + queen.width) and
   (self.x > queen.x) then
      return true;
   else
      return false;
   end
end