local group = require('utils/group')
local groups = require('utils/groups')

local function sum_group(filename)
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

local function sum_unique(filename)
    local files = io.open(filename, "r")
    local travel_group = {}

    for line in files:lines() do
        if line == "" then
            groups[0] = travel_group
            travel_group = {}
        else
            table.insert(travel_group, line)
        end
    end

    groups[0] = travel_group

    files:close()

    return groups.sum
end

print(sum_group("data/input"))
print(sum_unique("data/input"))
