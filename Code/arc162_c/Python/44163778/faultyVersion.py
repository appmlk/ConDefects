import sys
sys.setrecursionlimit(10**7)
input = sys.stdin.readline
import pypyjit
pypyjit.set_param('max_unroll_recursion=-1')
def mp():return map(int,input().split())
def lmp():return list(map(int,input().split()))
def lm1(LIST): return list(map(lambda x:x-1, LIST))
def mps(A):return [tuple(map(int, input().split())) for _ in range(A)]
def stoi(LIST):return list(map(int,LIST))
def itos(LIST):return list(map(str,LIST))
def atoi(LIST): return [ord(i)-ord("a") for i in LIST]
def Atoi(LIST): return [ord(i)-ord("A") for i in LIST]
def LT(LIST,N): return LIST[bisect.bisect_left(LIST,N)-1]
def LE(LIST,N): return LIST[bisect.bisect_right(LIST,N)-1]
def GT(LIST,N): return LIST[bisect.bisect_right(LIST,N)]
def GE(LIST,N): return LIST[bisect.bisect_left(LIST,N)]
def bitA(X,A):return X & 1<<A == 1<<A
import math
import bisect
import heapq
import time
from copy import copy as cc
from copy import deepcopy as dc
from itertools import accumulate, product
from collections import Counter, defaultdict, deque
def ceil(U,V):return (U+V-1)//V
def modf1(N,MOD):return (N-1)%MOD+1
inf = (1<<63)-1
mod = 998244353


def check_mex(LIST):
    t = []
    for i in range(n+1):
        if LIST[i] == 0:t.append(i)
        if len(t) == 2:break
    return t

def merge(mae,now):
    A = have[mae]
    B = have[now]
    C = [0]*(n+1)
    for i in range(n+1):
        C[i] = A[i] + B[i]
    return C


def dfs(now,mae):
    if a[now] != -1:
        have[now][a[now]] += 1
    for i in edge[now]:
        if i == mae:continue
        dfs(i,now)
    if a[now] == -1:
        mitei[now] += 1
    mitei[mae] += mitei[now]
    have[mae] = merge(mae,now)
    mex = check_mex(have[mae])
    if mitei[now] == 0 and check_mex(have[now])[0] == k:
        flag[0] = 1
    if mitei[now] == 1 and (mex[0] == k or mex[1] == k):
        flag[0] = 1


t = int(input())
for _ in range(t):
    n,k = mp()
    p = lm1(lmp())
    a = lmp()
    flag = [0]
    edge = [[] for i in range(n)]
    for i in range(n-1):
        edge[i+1].append(p[i])
        edge[p[i]].append(i+1)
    aset = set(a)
    mitei = [0]*(n+1)
    have = [[0]*(n+1) for _ in range(n+1)]
    dfs(0,-1)
    mitei.pop()
    if flag[0]:print("Alice")
    else:
        print("Bob")
