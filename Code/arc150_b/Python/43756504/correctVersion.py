import bisect, heapq, sys, math, copy, itertools, decimal
from collections import defaultdict, deque
sys.setrecursionlimit(10**7)
def INT(): return int(input())
def MI(): return map(int, input().split())
def MS(): return map(str, input().split())
def LI(): return list(map(int, input().split()))
def LS(): return list(map(str, input().split()))
def pr_line(itr): print(*itr, sep='\n')
def pr_mtx(matrix): [print(*row) for row in matrix] 
INF = 1<<62


T = INT()
ANS = []
for _ in range(T):
    A, B = MI()
    Nset = set()
    for i in range(1, B+1):
        if i ** 2 > B: break
        Nset.add(i)
        Nset.add(math.ceil(B / i))
    
    ans = INF
    for n in Nset:
        k = math.ceil(B / n)
        x = max(0, math.ceil(B / k) - A)
        y = (A + x) * k - B
        ans = min(ans, x + y)
    ANS.append(ans)
pr_line(ANS)