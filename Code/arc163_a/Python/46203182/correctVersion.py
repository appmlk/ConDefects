import sys
from numba import njit, i4

input = sys.stdin.readline


def read():
    return list(map(int, input().strip().split()))


t = int(input())


def solve():
    n = int(input())
    s = input().strip()

    if max(s[1:]) > s[0]:
        return 1
    if max(s[1:]) < s[0]:
        return 0
    n = len(s)
    z = [0] * n
    r = l = 0
    for i in range(1, n):
        if i <= r:
            z[i] = min(z[i - l], r - i + 1)
        while i + z[i] < n and s[z[i]] == s[i + z[i]]:
            z[i] += 1

        if i + z[i] - 1 > r:
            r = i + z[i] - 1
            l = i

        if z[i] < i and  i + z[i] < n and s[i + z[i]] > s[z[i]] or z[i] > i or z[i] == i and i * 2 < n:
            return 1
    return 0


for _ in range(t):
    if solve():
        print("Yes")
    else:
        print("No")
