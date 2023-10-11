import sys
input = sys.stdin.readline
inf = float('inf')


def read(dtype=int):
    return list(map(dtype, input().split()))


n = int(input())
a = read()
c = 0
for i in a:
    c ^= i
for j in range(30):
    if all(i >> j & 1 for i in a):
        print("Win")
        exit()

print("Win" if n & 1 or c in a else "Lose")
