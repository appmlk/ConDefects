
import sys,random,bisect
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from itertools import permutations
from math import gcd,log

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

"""
- 全体加算:yをクソデカにする 全体減産も可能

N=1の場合:自明

N=2の場合
a=bならc=dが必要でN=1に帰着 a < b とする
全体加算して 0,a(a≠0) に帰着
0,a からは x,y = 1,a+1 として 1,0 x,y = b,1+b として 0,b に変化できるので
0,a から 0,d-c にして全体加算

N=3の場合

"""

def solve_distinct(N,A,B):
    if N == 1:
        res = []
        if A[0] <= B[0]:
            res.append((B[0]-A[0],10**18))
        else:
            res.append((10**18-(A[0]-B[0]),10**18))
        return res
    for i in range(N):
        for j in range(N):
            if A[i] == A[j] and B[i]!=B[j]:
                return [-1]
    
    res = []
    M = max(max(B),max(A)) + 1

    idx = [i for i in range(N)]
    idx.sort(key=lambda i:A[i])

    goal = [-1] * N
    pre = -1
    for i in idx:
        r = B[i]
        """
        x >= (pre-r+Q-1)//Q
        """
        if r < pre+M:
            x = (pre+M-r+M-1)//M
            r += M * x
        assert r-pre >= M
        goal[i] = r
        pre = r
    
    for i in (idx[0],):
        r = B[i]
        """
        x >= (pre-r+Q-1)//Q
        """
        if r < pre+M:
            x = (pre+M-r+M-1)//M
            r += M * x
        assert r-pre >= M
        goal[i] = r
        pre = r

    
    tmp = A.copy()
    
    #x = 1
    #y = tmp[idx[-1]] + 1
    #res.append((x,y))
    #for i in range(N):
        #tmp[i] = (tmp[i]+x) % y
        
    

    for k in range(1,N)[::-1]:
        d = goal[idx[(k+1)%N]] - goal[idx[k]] - tmp[idx[(k+1)%N]]
        assert 0 <= d
        
        x = d
        y = (tmp[idx[k]]+d)
        res.append((x,y))
        for i in range(N):
            tmp[i] = (tmp[i]+x) % y
        print(tmp)
        
    """
    x = goal[idx[0]]
    y = 10**18
    res.append((x,y))
    for i in range(N):
        tmp[i] = (tmp[i]+x) % y
    """


    res.append((goal[idx[1]]%M,M))
    for i in range(N):
        tmp[i] = (tmp[i]+goal[idx[1]]) % M
        assert tmp[i] == B[i]
    return res

def solve(N,A,B):
    if N == 1:
        res = []
        if A[0] <= B[0]:
            res.append((B[0]-A[0],10**18))
        else:
            res.append((10**18-(A[0]-B[0]),10**18))
        return res
    for i in range(N):
        for j in range(N):
            if A[i] == A[j] and B[i]!=B[j]:
                return [-1]
    
    idx = []
    new_A = []
    new_B = []
    for i in range(N):
        if A[i] not in new_A:
            new_A.append(A[i])
            new_B.append(B[i])
    return solve_distinct(len(new_A),new_A,new_B)

def checker(N,_A,B,res):
    A = _A.copy()
    assert len(res) <= N
    for x,y in res:
        assert 0 <= x < y
        for i in range(N):
            A[i] = (A[i]+x) % y
    return A == B



while False:
    N = random.randint(4,10)
    A = [random.randint(0,10) for i in range(N)]
    B = [random.randint(0,10) for i in range(N)]
    res = solve(N,A,B)
    if res!=[-1]:
        assert checker(N,A,B,res)
    print("OK")


N = int(input())
A = li()
B = li()

res = solve(N,A,B)

if res == [-1]:
    print("No")
else:
    assert checker(N,A,B,res)
    print("Yes")
    print(len(res))
    for x,y in res:
        print(x,y)

    




