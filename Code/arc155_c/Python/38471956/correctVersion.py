import math
import sys
sys.setrecursionlimit(500_000)
from collections import defaultdict
from collections import Counter

n = int(input())
a = list(map(int, input().split()))
b = list(map(int, input().split()))

def oddin3(xs):
    m = len(xs)
    for i in range(m - 2):
        oc = (xs[i] % 2) + (xs[i + 1] % 2) + (xs[i + 2] % 2)
        if oc == 2:
            return True
    return False        

def oesplit(xs):
    ans = []
    for x in xs:
        if len(ans) == 0 or x % 2 != ans[-1][0] % 2:
            ans.append([])
        ans[-1].append(x)
    odd, even = [], []
    for x in ans:
        if len(x) > 2 and x[0] % 2 == 0:
            x.sort()
        if len(x) > 0:
            if x[0] % 2 == 0:
                even.append(x)
            else:
                odd.extend(x)                
    # return ans
    return odd, even

def solve():
    if a == b:
        return True
    if Counter(a) != Counter(b):
        return False
    oa = oddin3(a)
    ob = oddin3(b)
    ea = [x for x in a if x % 2 == 0]
    eb = [x for x in b if x % 2 == 0]
    if oa and ob:
        if len(ea) > 2:
            return True
        else:
            return ea == eb
    if oa or ob:
        return False
    if oesplit(a) != oesplit(b):
        return False
    return True

if solve():
    print('Yes')    
else:
    print('No')    
    