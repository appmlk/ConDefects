import heapq
import itertools
from collections import deque
from sys import stdin

N, M = map(int, input().split())
L = list(map(int, input().split()))

for i in range(N):
    L[i] += 1

ok = 10 ** 15
ng = -1

def check(num):
    wordLen = -1
    wordLines = 1
    for i in range(N):
        if wordLen+L[i] > num:
            wordLen = -1
            wordLines += 1
        wordLen += L[i]

    if wordLines <= M:
        return True
    else:
        return False

while ok-ng >= 2:
    srch = (ng+ok)//2
    c = check(srch)
    if c:
        ok = srch
    else:
        ng = srch

    # print(ok, ng)

ans = ng+1
print(ans)
