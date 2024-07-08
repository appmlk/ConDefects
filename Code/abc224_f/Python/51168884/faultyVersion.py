import io
import sys
import bisect
import math
from itertools import permutations, combinations
from heapq import heappush, heappop
from collections import deque
from collections import defaultdict as dd
sys.setrecursionlimit(10**7+10)

mod = 998244353

_INPUT = """\
31415926535897932384626433832795

"""



def main():
    S = input()
    n = len(S)
    dp1 = [0]*(n+1)
    # dp2 = [0]*(n+1)
    dp1[n] = 1
    dp1[n-1] = 1
    for i in range(n-2, 0, -1):
        dp1[i] = pow(2, n-1-i, mod)
    temp = 0
    for i in range(1, n+1):
        temp += dp1[i]*pow(10, i-1, mod)
        temp %= mod
    ans = 0
    for i in range(n, 0, -1):
        x = int(S[n-i])
        ans += x * temp
        temp -= dp1[i]*pow(10, i-1, mod)
        if i>1:
            temp += dp1[i]*pow(10, i-2, mod)
            dp1[i-1] += dp1[i]
        temp %= mod
        ans %= mod
    print(ans)

if __name__ == "__main__":
    sys.stdin = io.StringIO(_INPUT)
    main()
