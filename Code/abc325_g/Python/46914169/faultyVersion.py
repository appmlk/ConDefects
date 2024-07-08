from functools import lru_cache
from math import inf, ceil


def read_ints():
    return [int(x) for x in input().split(' ')]

def slv():
    s=input()
    K=int(input())
    n=len(s)

    @lru_cache(None)
    def dfs(i,j):
        if j<i:return 0
        if j==i:return 1
        res=j-i+1
        if s[i]=='o':
            for x in range(i+1,j+1):
                if s[x]=='f' and dfs(i+1,x-1)==0:
                    res=min(res,dfs(min(x+K+1,j+1),j))
        for x in range(i+1,j+1):
            if s[x]=='o':
                res=min(res,x-i+dfs(x,j))
        return res

    print(dfs(0,n-1))
T = 1
for _ in range(T):
    slv()
