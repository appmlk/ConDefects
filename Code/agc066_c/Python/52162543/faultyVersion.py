import sys, time, random
from collections import deque, Counter, defaultdict
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
inf = 2 ** 61 - 1
mod = 998244353

def solve():
    s = input()
    n = len(s)
    dp = [inf] * (n + 1)
    bdic = defaultdict(lambda: inf)
    dic = defaultdict(lambda: inf)
    dp[0] = 0
    dic[0] = 0
    cnt = 0
    if s[0] == 'B':
        bdic[0] = 0
    for i in range(n):
        dp[i + 1] = min(dp[i + 1], dp[i] + 1)
        if s[i] == 'A':
            cnt += 1
        else:
            cnt -= 2
        if s[i] == 'B':
            dp[i + 1] = min(dp[i + 1], dic[cnt])
        dp[i + 1] = min(dp[i + 1], bdic[cnt])
        dic[cnt] = min(dic[cnt], dp[i + 1])
        if s[i] == 'B':
            bdic[cnt] = min(bdic[cnt], dp[i + 1])
    print((n - dp[n]) // 3)
            
            
        
    
for _ in range(ii()):
    solve()