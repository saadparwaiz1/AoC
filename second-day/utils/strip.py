import re


def strip(string):
    pattern = r'([0-9]*)-([0-9]*) (.): (.*)\n'
    groups = re.match(pattern=pattern, string=string)
    return groups.groups()
