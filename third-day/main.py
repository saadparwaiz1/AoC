from utils import utils


def main(instruction):
    map = utils.create_map('data/input')
    starting_position = [0, 0]
    trees = 0

    while starting_position[0] < len(map) - 1:
        trees += utils.move(instruction, starting_position, map)
    return trees


if __name__ == '__main__':
    slope_1 = main((1, 1))
    slope_2 = main((1, 3))
    slope_3 = main((1, 5))
    slope_4 = main((1, 7))
    slope_5 = main((2, 1))

    print(slope_2)
    multiply = slope_1 * slope_2 * slope_3 * slope_4 * slope_5
    print(multiply)
