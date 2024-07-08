from collections import Counter
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

def solve():
    INF = 10**9

    N, M = map(int, input().split())
    ABs = [tuple(map(int, input().split())) for _ in range(N)]

    sumA = sum([A for A, B in ABs])
    #print('# sumA:', sumA)

    diffs = [B-A for A, B in ABs]
    cnt = Counter(diffs)
    #print('# diffs:', diffs)
    #print('# cnt:', cnt)

    def f(x):
        anss = []
        s = 1
        while True:
            if x-s >= 0:
                anss.append(s)
            else:
                break
            x -= s
            s *= 2
        if x > 0:
            anss.append(x)
        return anss

    dp = [INF] * (M+1)
    dp[sumA] = 0
    for diff, num in cnt.items():
        xs = f(num)
    #    print('\n##### (diff, num):', (diff, num), '/ xs:', xs)
    #    print('# dp:', dp)
        for x in xs:
            d = diff*x
    #        print('### x:', x, '/ d:', d)
            dp2 = dp[:]
            for i in range(M+1):
                dpNow = dp[i]
                if dpNow == INF:
                    continue
                c2 = dpNow+1
                i2 = i+d
                if c2 < dp2[i2]:
                    dp2[i2] = c2
            dp = dp2
    #        print('# dp:', dp)

    anss = []
    for i in range(M+1):
        ans = dp[i]
        if ans == INF:
            anss.append(-1)
        else:
            anss.append(ans)

    print('\n'.join(map(str, anss)))


solve()
