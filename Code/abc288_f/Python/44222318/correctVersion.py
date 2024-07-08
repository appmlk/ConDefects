import sys
input = lambda: sys.stdin.readline().strip()
MOD = 998244353

def solve():
    n = int(input())
    s = input()
    sum_f = 1
    f = 0
    for c in s:
        x = int(c)
        f = (f * 10 + sum_f * x) % MOD
        sum_f += f
    print(f)

solve()
