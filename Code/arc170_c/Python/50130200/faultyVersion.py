'''2023年12月17日14:57:16, ac上抄的'''
'''cf的 py3.8 没有cache'''
# from sortedcontainers import *  #cf没有
# from math import *    #pow出现问题 math.pow里没有mod
from heapq import *
from collections import *
from bisect import *
from itertools import *
from functools import lru_cache
from copy import *
import sys

# sys.setrecursionlimit(2147483647)  #pypy mle
sys.setrecursionlimit(17483647)


def main():
    # from math import gcd, floor, ceil, sqrt, isclose, pi, sin, cos, tan, asin, acos, atan, atan2, hypot, degrees, radians, log, log2, log10
    # from heapq import heappush, heappop, heapify, heappushpop, heapreplace, nlargest, nsmallest
    # from itertools import count, cycle, accumulate, chain, groupby, islice, product, permutations, combinations, combinations_with_replacement
    inf = 3074457345618258602  # 注意一下
    mod = 998244353  # ac

    def py():
        print("Yes")  # ac严谨

    def pn():
        print("No")

    # 按照输入的行, 一行一行的取值,每行得到的值都是列表.
    il = lambda: list(map(int, input().split()))  # 单个 n = i()[0]  列表 l = i() #input_list
    ix = lambda: il()[0]  # 单个数字  #input_x
    # 列表输出数字串, 例子 [1,2,3] printout: 1 2 3
    pl = lambda a: print(" ".join(map(str, a)))  # print_list
    en = enumerate

    '''代码'''
    # t = ix()
    # for _ in range(t):
    n,m = il()
    s = il()
    if m >= n-1 and n >= 2:
        print(pow(m, s.count(0), mod))
        return
    dp = [[0]*(m+2) for _ in range(n+1)]
    dp = [[0]*(n+1) for _ in range(n+1)]
    # dp = [[0] * (N + 1) for _ in range(N + 1)]
    # dp[0][0] = 1
    # for i in range(N):
    #     s = S[i]
    #     if s == 1:
    #         for j in range(min(M + 1, N)):
    #             dp[i + 1][j + 1] += dp[i][j]
    #             dp[i + 1][j + 1] %= MOD99
    #     else:
    #         for j in range(min(M, N)):
    #             dp[i + 1][j + 1] += (dp[i][j] * (M - j)) % MOD99
    #             dp[i + 1][j + 1] %= MOD99
    #         for j in range(1, min(M + 2, N)):
    #             dp[i + 1][j] += (dp[i][j] * j) % MOD99
    #             dp[i + 1][j] %= MOD99
    # # print(dp)
    # print(sum(dp[-1]) % MOD99)
    dp[0][0] = 1
    for i,v in en(s):
        if v == 1:
            # for j in range(0, m+1):
            for j in range(min(m+1, n)):
                dp[i+1][j+1] += dp[i][j]
                dp[i + 1][j + 1] %= mod
        else:
            # for j in range(0, m):
            for j in range(min(m,n)):
                dp[i+1][j+1] =  (dp[i+1][j+1] + dp[i][j]*(m-j)) % mod
            # for j in range(1,m+2):
            for j in range(1,min(m+2, n)):
                dp[i+1][j] += (dp[i][j] * j)
                dp[i + 1][j] %= mod
    print(dp)
    print(sum(dp[-1])%mod)



'''test
10 1000
0 0 1 0 0 0 1 0 1 0

329696899
'''

''''''
main()

'''

'''
