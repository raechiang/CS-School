Heal = PowerUp:extend();

-- Really simple thing...
function Heal:new(playerX)
   -- x, imageFile, spawnRate
   Heal.super.new(self, playerX, "maximTomato.png", 0.05, "heal");
end