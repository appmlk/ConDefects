import sys, itertools, functools, collections, bisect, math
from math import factorial as fact
input = sys.stdin.readline

rs  = lambda: input().strip()
ri  = lambda: int(input())
rmi  = lambda: map(int, input().split())
ra = lambda: [int(x) for x in input().split()]

INF = 10**18
MOD = 10**9+7


    
def solve():
    ans = 0
    for mask in range(1 << m):
        cur = 0
        for i in range(m):
            if mask & 1 << i:
                for val in tmp[i]:
                    cur |= 1 << val
        if bin(cur).count('1') == n:
            ans += 1
    return ans
        
    
        
test_case = 1
for _ in range(test_case):
    n, m = rmi()
    #for mask in range(1 << m):
    tmp = []
    for _ in range(m):
        c = ri()
        tmp.append(ra())
        
    print(solve())