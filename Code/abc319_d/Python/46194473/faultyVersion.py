# -------------------------------------------------
# 基本ライブラリ（PyPy3対応）
# -------------------------------------------------
import sys
import math
import bisect  #二分探索
import itertools as itert
from queue import Queue
import heapq
from collections import deque
from decimal import Decimal, ROUND_HALF_UP
sys.setrecursionlimit(10**7)
# -------------------------------------------------
# 入力メソッド
# -------------------------------------------------
def ST():
    return input() #str型
def IN():
    return int(input()) #int型
def FL():
    return float(input()) #float型
 
def SL():
    return input().split() #strリスト・複数列併用
 
def IM():
    return map(int, input().split()) #int複数列
def IL():
    return list(map(int, input().split())) #intリスト
 
def Lis():
    return list(input()) #ひとつひとつの「文字」をリストにしてインプット
 
def IR(N): #N行整数をリスト化
    x = []
    for _ in range(N):
        x.append(int(input())) 
    return x
 
def SR(N): #N行文字列をリスト化
    x = []
    for _ in range(N):
        x.append(input())
    return x
 
def IRM(N): #列に変数が並ぶN行データ
    z = [map(int, input().split()) for _ in range(N)]
    return [list(i) for i in zip(*z)]
 
def SGrid(N): #N行文字列二次元グリッドをリスト化
    x = []
    for i in range(N):
        x.append(list(input()))
    return x

def IMatrix(N): #一般のN行行列
    return [list(map(int, input().split())) for l in range(N)]

# -------------------------------------------------

N, M = IM()
L = IL()
S = sum(L)
ans = 0

def index_count(L, W): ##  #不適合なWが選ばれた場合-1を出力する
    cnt = 0
    ans = 0
    for i in range(len(L)):
        if cnt >= W:
            ans = 10**10
            break
        if cnt == 0:
            cnt += L[i]
        else:
            cnt += 1 + L[i]
        if cnt >= W:
            ans += 1
            cnt = L[i]
    return ans + 1


left = 0
right = S + N
while left + 1 < right:
    W = (left+right)//2
    if index_count(L, W) > M:
        left = W
    else:
        right = W
    #print(index_count(L, W), left, right)

print(left)