#コーディングはこのセルで
from collections import deque,defaultdict
from fractions import Fraction
from itertools import permutations
from functools import cmp_to_key
import math,sys,heapq,random,bisect,copy
def LMI() : return list(map(int,input().split()))
def LMS() : return list(map(str,input().split()))
def MI() : return map(int,input().split())
def LLI(N) : return [LMI() for _ in range(N)]
def LLS(N): return [LMS() for _ in range(N)]
def LS(N) : return [input() for _ in range(N)]
def LI(N) : return [int(input()) for _ in range(N)]
def II() : return int(input())

#入力
T=II()
for _ in range(T):
    N=II()
    S=input()
    count=0
    for i in range(N):
        if S[i]=='1':
            count+=1
    if count%2==1:
        print(-1)
    else:
        if count!=2:
            print(count//2)
        else:
            flg=0
            for i in range(N-1):
                if S[i]=='1' and S[i+1]=='1':
                    flg=1
            if flg:
                if N==3:
                    print(-1)
                elif N==4:
                    if S=='0110':
                        print(3)
                    else:
                        print(2)
                else:
                    print(2)
            else:
                print(1)