from string import ascii_uppercase

N, X = map(int, input().split())

print(ascii_uppercase[(X - 1) // N])
