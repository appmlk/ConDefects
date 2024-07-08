from collections import deque
import sys
import bisect
import math

input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
li = lambda: list(map(int, input().split()))
INF = 2**63 - 1
MOD = 998244353
move = ((1, 0), (0, 1), (-1, 0), (0, -1))
move_diag = ((0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1), (-1, 0), (-1, -1))

# ---------------------------------------------- Template END ---------------------------------------------- #

N = int(input())
S = input()

# count number of occurances for each number in S
count = [0 for _ in range(10)]  # count for 0 - 9
for c in S:
    count[int(c)] += 1


# number of squared number we can create
ans = 0

# for all possible squared numbers, check if it is possible to create by rearraning S
for i in range(1, math.ceil(math.sqrt(10**N))):
    squared = i * i
    count_squared = [0] * 10

    for c in str(squared):
        count_squared[int(c)] += 1

    # only for 0s, we don't need an exact match, but equal or more 0s in S
    if count_squared[0] < count[0]:
        count_squared[0] = count[0]  # enough 0s so set equal to pass

    if count_squared == count:
        ans += 1

print(ans)
