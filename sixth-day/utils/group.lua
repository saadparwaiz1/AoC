local group = {
  sum = 0
}

local metagroup = {}

setmetatable(group, metagroup)


local function add(table, key, value)
  local array = {}
  local count = 0

  for c in value:gmatch(".") do
    if array[c] == nil then
      array[c] = true
      count = count + 1
    end
  end

  table["sum"] = table["sum"] + count
end

metagroup.__newindex = add

return group
