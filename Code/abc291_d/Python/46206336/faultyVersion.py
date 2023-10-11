import sys, heapq, math, bisect, itertools
from collections import defaultdict, deque

def main():
    input = sys.stdin.readline
    sys.setrecursionlimit(10 ** 8)
    INF = float('inf')
    MOD = 998244353

    N = int(input())
    cards = []
    for _ in range(N):
        A, B = map(int, input().split())
        cards.append((A, B))
    
    dp = [[0, 0] for _ in range(N)]
    dp[0] = [1, 1]
    for i in range(1, N):
        for j in [0, 1]:
            if cards[i][j] != cards[i-1][0]:
                dp[i][j] += dp[i-1][0]
                dp[i][j] %= MOD
            if cards[i][j] != cards[i-1][1]:
                dp[i][j] += dp[i-1][1]
                dp[i][j] %= MOD

    print(sum(dp[-1]))

if __name__ == "__main__":
    main()