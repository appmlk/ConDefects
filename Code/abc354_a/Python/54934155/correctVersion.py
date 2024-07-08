from math import log2, ceil

def strict_ceil(number):
    if number == ceil(number):
        return int(number + 1)
    return ceil(number)

H = int(input())
print(strict_ceil(log2(H + 1)))