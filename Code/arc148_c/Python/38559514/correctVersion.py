import sys
from collections import *
input = sys.stdin.readline
from math import *
def mrd(): return [int(x) for x in input().split()]
def rd(): return int(input())
MAXN = 2 * 10**5 + 5
INF = 10**16 * 2
mod = 10**9 + 7
#----------------------------------------------------------------------------------#
'''
https://atcoder.jp/contests/arc148/tasks/arc148_c

输入 n(2≤n≤2e5) q(≤2e5)，然后输入 p2,p3,...,pn 表示一棵根为 1 的树，pi 表示点 i 的父节点。
然后输入 q 个询问，每个询问先输入 m，然后输入 m 个互不相同的特殊节点 v1,v2,...,vm。所有询问的 m 之和不超过 2e5。

每个节点都有一盏灯，其中特殊节点的灯打开，其余节点的灯关闭。
每次操作，你可以选择一棵子树，切换子树内所有灯的开/闭状态。
对每个询问，回答：要使所有灯关闭，至少需要多少次操作。

try1: 
似乎自上而下模拟就好了
看错题了，这是多次询问，cao

try2:
找联通块，答案就是和其相邻的子节点格式+1
'''
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa != pb:
            if self.size[pa] > self.size[pb]:
                self.p[pb] = pa
                self.size[pa] += self.size[pb]
            else:
                self.p[pa] = pb
                self.size[pb] += self.size[pa]

    def reset(self, x):
        self.p[x] = x
        self.size[x] = 1

def solve():
    n, m = mrd()
    p = [i - 1 for i in mrd()]
    
    son = [0] * n
    for i in range(n - 1):
        #i+1 p[i]
        son[p[i]] += 1

    uf = UnionFind(n)

    for _ in range(m):
        t = mrd()
        t = [t[0]] + [t[i] - 1 for i in range(1, t[0] + 1)]
        s = set([t[i] for i in range(1, t[0] + 1)])
        ans = 0
        for i in range(1, t[0] + 1):
            ans += son[t[i]]
            if p[t[i] - 1] in s and t[i]:
                uf.union(p[t[i] - 1], t[i])

        roots = set()
        for i in range(1, t[0] + 1):
            u = t[i]
            roots.add(uf.find(u))
            if p[u - 1] in s and u:
                ans -= 1
        
        print(ans + len(roots))

        for i in range(1, t[0] + 1):
            uf.reset(t[i])
            uf.reset(p[t[i] - 1])


if __name__ == "__main__":
    solve()