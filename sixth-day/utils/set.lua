local Set = {}

Set.mt = {}

local function new(t)
    local set = {}

    setmetatable(set, Set.mt)

    for _, l in ipairs(t) do set[l] = true end

    return set
end

local function intersection(a, b)
    local res = Set.new({})

    for k in pairs(a) do res[k] = b[k] end

    return res
end

local function split(string)
    local t = {}

    for str in string:gmatch(".") do table.insert(t, str) end

    return t
end

function Set.tostring(set)
    local s = "{"
    local sep = ""
    for e in pairs(set) do
        s = s .. sep .. e
        sep = ", "
    end
    return s .. "}"
end

function Set.print(s) print(Set.tostring(s)) end

function Set.count(set)
    count = 0
    for e in pairs(set) do count = count + 1 end

    return count
end

Set.mt.__mul = intersection

Set.split = split
Set.new = new

return Set
