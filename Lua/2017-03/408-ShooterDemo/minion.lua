-- The simple minion
Minion = Enemy:extend();

function Minion:new(x, y)
   -- super x, y, width, height, speed, health, cooldown, damageModifier
   Minion.super.new(self, x, y, MINION_WIDTH, MINION_HEIGHT, 0, MINION_HEALTH, MINION_CD, MINION_DMG_MOD);
   self.color = {18, 91, 209};
   self.pSpeed = 600;
end

-- update. The minion only shoots, poor fools are stationary. Cannon fodder.
function Minion:update(dt)
   if (self.dead == false) then
      self:attack(dt);
   end
end

-- draws the minion
function Minion:draw()
   love.graphics.setColor(self.color);
   love.graphics.rectangle("fill", self.x, self.y, self.width, self.height);
end

-- Gasp! They didn't actually die! The sight of their beloved queen awakening
-- fills them with d e t e r m i n a t i o n . . .
-- (In other words the dead rise and become stronger!)
function Minion:respawn()
   self.health = MINION_HEALTH + 2;
   self.dead = false;
   self.cooldown = MINION_CD - 2;
end