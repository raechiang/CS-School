-- This was someone else's simple implementation of OOP support!
--
--+----------------------------------------------------------------------------+
-- classic
--
-- Copyright (c) 2014, rxi
--
-- This module is free software; you can redistribute it and/or modify it under
-- the terms of the MIT license. See LICENSE for details.
--+----------------------------------------------------------------------------+

-- As expected, we are representing the "classes" as tables
local Object = {}
-- The metamethod __index is crucial for inheritance because in the case that a
-- desired function or value is not found in our object/table (recall tables are
-- basically our objects), Lua calls this __index metamethod (if it has been
-- established). Note, metamethod is kind of a misnomer because they don't
-- always have to be methods/functions--they can be tables, as is the case here!
Object.__index = Object

function Object:new()
end

-- This is used to create a new class that "extends" this Object type, which
-- can be used to establish a hierarchy of classes (you can see its use in the
-- big Ship hierarchy
function Object:extend()
  local cls = {}
  for k, v in pairs(self) do
    if k:find("__") == 1 then
      cls[k] = v
    end
  end
  cls.__index = cls
  cls.super = self
  setmetatable(cls, self)
  return cls
end

-- I actually didn't use this, but apparently it's used for mixins (a class with
-- methods that other classes can use without being relatives)
function Object:implement(...)
  for _, cls in pairs({...}) do
    for k, v in pairs(cls) do
      if self[k] == nil and type(v) == "function" then
        self[k] = v
      end
    end
  end
end

-- This checks an object's type (use: obj:is(Object) --> true/false)
function Object:is(T)
  local mt = getmetatable(self)
  while mt do
    if mt == T then
      return true
    end
    mt = getmetatable(mt)
  end
  return false
end

-- Honestly I'm not sure what this is for, maybe it's just meant to be overriden
-- And despite it having a __ it's not actually a Lua metamethod
function Object:__tostring()
  return "Object"
end

-- __call makes something that is not a function act like a function
function Object:__call(...)
  local obj = setmetatable({}, self)
  obj:new(...)
  return obj
end


return Object