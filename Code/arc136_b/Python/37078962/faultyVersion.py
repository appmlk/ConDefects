INF = 1 << 64
mod1 = 10**9 + 7
mod2 = 998244353
dir = [(1, 0), (0, 1), (-1, 0), (0, -1)]
import sys
from collections import defaultdict, deque
#from functools import lru_cache
#@lru_cache
input = sys.stdin.readline
sys.setrecursionlimit(10**9)
def ni(): return int(input())
def na(): return map(int, input().split())
def nl(): return list(map(int, input().split()))
def ns(): return input().strip()
def nsl(): return list(input().strip())

d = defaultdict(list)
N = ni()
A = nl()
B = nl()
if sorted(A) != sorted(B):
    print("No")
    exit()

for i, a in enumerate(A):
    d[a].append(i)

B_rev = [None] * N
for i, b in enumerate(B):
    B[i] = d[b].pop()
    B_rev[B[i]] = i

#print(B)
#print(B_rev)
for i in range(N-2):
    ini = B_rev[i]

    while i != ini:
        dif = ini - i
        if dif >= 2:
            a, b, c = B[ini], B[ini-1], B[ini-2]
            B_rev[a], B_rev[b], B_rev[c] = ini-2, ini, ini-1 
            B[ini], B[ini-1], B[ini-2] = B[ini-1], B[ini-2], B[ini]
            ini -= 2
        
        elif dif == 1: 
            a, b, c = B[ini-1], B[ini], B[ini+1]
            B_rev[a], B_rev[b], B_rev[c] = ini+1, ini-1, ini 
            B[ini-1], B[ini], B[ini+1] = B[ini], B[ini+1], B[ini-1]
            ini -= 1


if B != list(range(N)):
    print("No")
else:
    print("Yes")

