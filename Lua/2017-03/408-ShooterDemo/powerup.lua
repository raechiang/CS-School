-- PowerUps for the player
PowerUp = Object:extend();

function PowerUp:new(playerX, imageFile, name)
   self.x = self:spawn(playerX);
   self.y = PLAYER_Y0;
   self.image = love.graphics.newImage(imageFile);
   self.width = self.image:getWidth();
   self.height = self.image:getHeight();
   self.name = name;
   self.lifetime = 3;
   self.death = false;
end

-- update
function PowerUp:update(dt)
   if (self.death == false) then
      self.lifetime = self.lifetime - dt;
   else
      if (self.lifetime == 0) then
         self.death = true;
      end
   end
end
-- draw
function PowerUp:draw()
   if self.death == false then
      love.graphics.setColor(255, 255, 255);
      love.graphics.draw(self.image, self.x, self.y);
   end
end

-- it wouldn't be very interesting if the powerup spawned in the same place
-- every time... I think.
function PowerUp:spawn(playerX)
   local width = WINDOW_WIDTH;
   if (playerX <= 200) then
      return width - playerX;
   else
      return playerX - width/2;
   end
end