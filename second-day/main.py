from utils import utils


def main(args):
    with open("data/input", "r") as d:
        lines = d.readlines()

    if args[1] == 'count':
        lines = sum(
            map(lambda x: int(utils.validate_count(*utils.strip(x))),
                lines))
        print(lines)
    else:
        lines = sum(
            map(lambda x: int(utils.validate_index(*utils.strip(x))),
                lines))
        print(lines)


if __name__ == "__main__":
    main(['main', 'count'])
    main(['main', 'index'])
