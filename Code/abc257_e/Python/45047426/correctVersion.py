import sys
from functools import lru_cache
from collections import defaultdict


sys.setrecursionlimit(10**9)
def I(): return input()
def IS(): return input().split()
def II(): return int(input())
def LI(): return list(input())
def MI(): return map(int,input().split())
def LMI(): return list(map(int,input().split()))
def LLMI(number): return [list(map(int,input().split())) for _ in range(number)]

def generate_input()->list:
    import random
    inputdata = []
    n = random.randint(0, 100)
    inputdata.append(n)
    return inputdata


def simple_solve(n):
    ans = 0
    return ans


def solve(n, c):
    min_cost = min(c)
    digits = n // min_cost
    new_c = []
    for v in c:
        new_c.append(v - min_cost)
    remain_cash = n - (digits * min_cost)
    new_c.reverse()
    ans = []
    for i, v in enumerate(new_c):
        if remain_cash >= v:
            if v == 0:
                cnt = digits - len(ans)
            else:
                cnt = remain_cash // v
                remain_cash -= cnt * v
            for j in range(cnt):
                ans.append(str(9-i))
        else:
            continue
        if len(ans) == digits:
            break

    return ''.join(ans)


if __name__=='__main__':
    test = False
    if test:
        inputdata = generate_input()
        a = simple_solve(*inputdata)
        b = solve(*inputdata)
        if a != b:
            print(*inputdata)
            print(a, b)
            exit(1)
    else:
        inputdata = []
        inputdata.append(II())
        inputdata.append(LMI())
        print(solve(*inputdata))