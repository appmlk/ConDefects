import sys
#input = sys.stdin.readline
#input = sys.stdin.buffer.readline #文字列はダメ
#sys.setrecursionlimit(1000000)
import math
#import bisect
#import itertools
#import random
#from heapq import heapify, heappop, heappush
#from collections import defaultdict 
#from collections import deque
#import copy #DeepCopy: hoge = [_[:] for _ in hogehoge]
#from functools import lru_cache
#@lru_cache(maxsize=None)
#MOD = pow(10,9) + 7
MOD = 998244353
#dx = [1,0,-1,0]
#dy = [0,1,0,-1]
#dx8 = [1,1,0,-1,-1,-1,0,1]
#dy8 = [0,1,1,1,0,-1,-1,-1]

def two_three(x):
    L = []
    while x > 0:
        L.append(x%3)
        x //= 3
    L = set(L)
    if 2 in L:
        return False
    else:
        return True

def main():
    N,M = map(int,input().split()); MAX = pow(10,7)
    num = 0
    val = 1
    total = 0
    ans = []
    while num < N:
        if two_three(val):
            ans.append(val*3)
            total += val*3 #ここで3倍しておくことで3進数としたときの1桁目を常に0として後で調整可能とする。
            num += 1
        val += 1
    # print(ans)

    idx = 0
    while total%N != 0:
        ans[idx] += 1
        idx += 1
        total += 1
    
    # print(ans)
    dif = (total - M)//N
    ans = [v - dif for v in ans]
    print(*ans)


if __name__ == '__main__':
    main()