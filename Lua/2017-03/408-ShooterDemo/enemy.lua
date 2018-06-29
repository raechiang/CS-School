-- The Enemy "class", which extends the Ship "class"
Enemy = Ship:extend();

function Enemy:new(x, y, width, height, speed, health, cooldown, damageModifier)
   -- equivalent to super(x, y, width, height, speed, health)
   Enemy.super.new(self, x, y, width, height, speed, health, damageModifier);
   self.cooldown = cooldown;
   -- It seemed more interesting for them to have at least some variation in
   -- their attack patterns, since enemies of the same class share the same
   -- cooldown value. Note, math.random() creates a real 0-1
   self.cooldownTimer = cooldown - math.random();
end

-- Enemy attacks approximately every whatever seconds minus whatever random roll
-- between 0 and 1. Actually I don't know if it's inclusive or not
function Enemy:attack(dt)
   if (self.cooldownTimer <= 0) then
      self:shoot(pSpeed);
      self.cooldownTimer = self.cooldown - math.random();
   else
      self.cooldownTimer = self.cooldownTimer - dt;
   end
end

-- Enemy shoots
function Enemy:shoot(pSpeed)
   -- projectiles must be instantiated with x, y, speed, damage, color
   table.insert(enemyProjectiles, Projectile(self.x + self.width/2, self.y + self.height, self.pSpeed, self.damageModifier, self.color));
end