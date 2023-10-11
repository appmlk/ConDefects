from collections import *
import heapq
import bisect

INF = float("inf")
MOD = 998244353
mod = 998244353


X = list(input())
num = [0]
for x in X:
    num.append(num[-1] + int(x))
num.reverse()
L = len(num)
ans = []
for i in range(L - 1):
    k = num[i]
    p, r = divmod(k, 10)
    ans.append(str(r))
    num[i + 1] += p
ans.reverse()
print("".join(ans))