-- The tank
Tank = Enemy:extend();

-- constructor
function Tank:new(x, y)
   -- super x, y, width, height, speed, health, cooldown, damageModifier
   Tank.super.new(self, x, y, TANK_WIDTH, TANK_HEIGHT, 0, TANK_HEALTH, TANK_CD, TANK_DMG_MOD);
   self.vertices = { -- x1,y1, x2,y2, ...
      (self.x + self.width/4), self.y,
      self.x, (self.y + self.height),
      (self.x + self.width), (self.y + self.height),
      (self.x + 3*(self.width/4)), self.y
   };
   self.color = {168, 8, 8};
   self.pSpeed = 400;
end

-- update. only attacks
function Tank:update(dt)
   if (self.dead == false) then
      self:attack(dt);
   end
end

-- draw
function Tank:draw()
   love.graphics.setColor(self.color);
   love.graphics.polygon("fill", self.vertices);
end

-- Gasp! They didn't actually die! The sight of their beloved queen awakening
-- fills them with d e t e r m i n a t i o n . . .
function Tank:respawn()
   self.health = TANK_HEALTH + 2;
   self.dead = false;
   self.cooldown = TANK_CD - 2;
end