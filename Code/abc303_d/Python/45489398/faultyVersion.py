import sys
import math
from collections import deque
sys.setrecursionlimit(10**6) #pythonの無限再起を防ぐための上限変更
input = sys.stdin.readline

# 303 D
X, Y , Z = map(int, input().split())
S = input()[:-1]

INF = 10000000000
Slen = len(S)
res = [[INF]*2 for i in range(Slen+1)]
res[0][0] = 0
for i in range(Slen):
    if S[i] == 'a':
        if res[i][0] != INF:
            res[i+1][0] = min(res[i+1][0], res[i][0] + X)
            res[i+1][1] = min(res[i+1][1], res[i][0] + Z + Y)

        if res[i][1] != INF:
            res[i+1][0] = min(res[i+1][0], res[i][1] + Z + X)
            res[i+1][1] = min(res[i+1][1], res[i][1] + Y)

    elif S[i] == 'A':
        if res[i][0] != INF:
            res[i+1][1] = min(res[i+1][1], res[i][0] + Z + X)
            res[i+1][0] = min(res[i+1][0], res[i][0] + Y)

        if res[i][1] != INF:
            res[i+1][1] = min(res[i+1][1], res[i][1] + X)
            res[i+1][0] = min(res[i+1][0], res[i][1] + Z + Y)

print(min(res[Slen][0], res[Slen][1]))