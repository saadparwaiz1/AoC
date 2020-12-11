def validate_count(low, high, char, string):
    count = 0

    for i in string:
        if i == char:
            count += 1

    return count <= int(high) and count >= int(low)

def validate_index(low, high, char, string):
    low = int(low) - 1
    high = int(high) -1

    try:
        return (string[low] == char and string[high] != char) or (string[low] != char and string[high] == char)
    except IndexError:
        return string[low] == char
