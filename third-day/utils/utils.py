def create_map(data):
    with open(data) as d:
        lines = map(lambda x: [c for c in x.rstrip()], d.readlines())
    return list(lines)


def move(instruction, starting_position, map):
    starting_position[0] += instruction[0]
    starting_position[1] += instruction[1]
    try:
        return map[starting_position[0]][starting_position[1]] == '#'
    except IndexError:
        delta = starting_position[1] - len(map[0])
        starting_position[1] = delta
        return map[starting_position[0]][starting_position[1]] == '#'
