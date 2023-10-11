from bisect import *

n = int(input())
P = [int(x) for x in input().split()]
Q = [int(x) for x in input().split()]

Qpos = [-1]*(n+1)
for i in range(n):
    Qpos[Q[i]] = i

LIS = [10**9]*n
for i in range(n):
    lst = []
    for j in range(P[i],n+1,P[i]):
        lst.append(Qpos[j])
    lst.sort(reverse=True)
    for j in lst:
        LIS[bisect_left(LIS,j)] = j

ans = n
for i in range(n):
    if LIS[i]==10**9:
        ans = i
        break

print(ans)