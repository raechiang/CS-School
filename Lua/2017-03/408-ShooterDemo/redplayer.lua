-- You, but red.
RedPlayer = Player:extend();

-- Initially I planned to have three or four player types, but I opted not to
-- because it's faster to just get into the game than have some selection screen
-- Maybe I could have randomized the player type...
function RedPlayer:new()
   -- super speed, health, damageModifier
   RedPlayer.super.new(self, 250, RED_HEALTH, 1);
   self.color = {255, 0, 0};
end

-- draw
function RedPlayer:draw()
   love.graphics.setColor(self.color);
   love.graphics.rectangle("fill", self.x, self.y, self.width, self.height);
end