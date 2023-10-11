# 275d
import math

def rec(n, memo={}):
    if math.floor(n) == 0:
        return 1
    if n in memo:
        return memo[n]
    memo[n] = rec(n/2, memo) + rec(n/3, memo)
    
    return memo[n]
n = int(input())
rec(n)