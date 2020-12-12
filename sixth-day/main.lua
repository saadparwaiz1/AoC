local group = require('utils/group')

local function sum_unique(filename)
  local files = io.open(filename, "r")
  local string = ""

  for line in files:lines() do
    if line == "" then
      group[0] = string
      string = ""
    else
      string = string .. line
    end
  end

  group[0] = string

  files:close()

  return group.sum
end


print(sum_unique("data/input"))
