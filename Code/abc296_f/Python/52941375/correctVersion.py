class Bit:
    def __init__(self, n):
        self.size = n
        self.tree = [0] * (n + 1)
 
    def sum(self, i):#1インデックス
        s = 0
        while i > 0:
            s += self.tree[i]
            i -= i & -i
        return s
 
    def add(self, i, x):
        while i <= self.size:
            self.tree[i] += x
            i += i & -i

    def sum_interval(self, l, r): #l+1～rまでの合計
        return self.sum(r) - self.sum(l)

def inversion(a):
    a_sorted = sorted(set(a))
    d = {ai: i+1 for i, ai in enumerate(a_sorted)}
    inv = 0
    bit = Bit(len(a_sorted))
    for i, ai in enumerate(a):
        ai = d[ai]
        inv += i - bit.sum(ai)
        bit.add(ai, 1)
    return inv

########################################################


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
INF = float('inf')

N = INT()
A = LI()
B = LI()
if sorted(A) != sorted(B):
    print('No')
    exit()

if len(A) == len(set(A)):
    invA = inversion(A)
    invB = inversion(B)
    if (invA + invB) % 2 == 0:
        print('Yes')
    else:
        print('No')
else:
    print('Yes')