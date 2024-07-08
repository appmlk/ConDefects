import math
import sys
sys.setrecursionlimit(500_000)
from collections import defaultdict

def solve1(x, k, tmp, d):
    m = len(tmp)
    ans = 0 if len(tmp) == d + 2 else 1
    #print(f'ans={ans}, tmp={tmp}, d={d}')
    rest = tmp[m - 1] - x
    if rest < 0:
        return float('inf')
    j = m - 2
    while rest > 0:
        x = tmp[j]
        j -= 1
        if x <= rest:
            v = rest // x
            ans += v
            rest = rest % x
    return ans            
def solve(d, k, x):
    d1 = 0
    tmp = [0]
    ans = float('inf')
    while tmp[-1] < x:
        tmp.append(tmp[-1] + k ** d1)
        ans = min(ans, solve1(x, k, tmp, d))
        d1 += 1
    return ans
t = int(input())
for _ in range(t):
    d, k, x = list(map(int, input().split()))
    print(solve(d, k, x))