local groups = {sum = 0}
local set = require('utils/set')

local metagroup = {}
setmetatable(groups, metagroup)

local function add(table, key, value)
    local universal = set.new(set.split("abcdefghijklmnopqrstuvwxyz"))

    for k, v in pairs(value) do
        local i_set = set.new(set.split(v))
        universal = universal * i_set
    end

    groups["sum"] = groups["sum"] + set.count(universal)
end

metagroup.__newindex = add

return groups
