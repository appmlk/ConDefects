import math
import sys
import random
from collections import deque
from itertools import product
debug = lambda name, value, *args, **kwargs: print( f"{name}: {value}", *args,  file=sys.stderr, **kwargs)

mod = 1000000007
m = random.randint(1,1000000)
iinf = 100000000000000005 #1e18 + 5
 
input = lambda: sys.stdin.readline().strip()
#sys.stdin = open('input.txt', 'r') 
#sys.stdout = open('output.txt', 'w')

dr = [1,-1,0,0]
dc = [0,0,1,-1]
 
def main():
    n,k = map(int,input().split())
    *v, = map(int,input().split())
    dp = [[iinf]*2 for _ in range(k+1)]
    dp[0][0] = 0 
    for i in range(k):
        if i+2 <= k:
            for j in range(2):
                dp[i+2][j] = min(dp[i+1][j], dp[i][j] + v[i+1] - v[i])
        dp[i+1][1] = min(dp[i+1][1], dp[i][0])
    print(min(dp[k][0], dp[k][1]))

t=1
#t = int(input())
for _ in range(t):
    main()