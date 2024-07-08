# https://atcoder.jp/contests/arc179/tasks/arc179_a
# Goal: all values less than K appear before numbers more than K in array's psa
# Case 1: K < 0: extra option to make everything in psa >= 0
# General case: sort array for smaller elements to come first

from itertools import accumulate
import sys


def check(psa):
    # first occurrence of number >=K in psa
    earliest = 9999999999
    for i in range(N + 1):
        if psa[i] >= K:
            earliest = i
            break
    # check if any number after the first >=K is less than K
    for i in range(earliest + 1, N + 1):
        if psa[i] < K:
            return False
    return True


N, K = map(int, input().split())
arr = list(map(int, input().split()))

found = False
if check([0] + list(accumulate(sorted(arr)))):
    found = True
    arr.sort()
if not found and K < 0 and check([0] + list(accumulate(sorted(arr, reverse=True)))):
    found = True
    arr.sort(reverse=True)

if found:
    print("Yes")
    print(*arr)
else:
    print("No")
