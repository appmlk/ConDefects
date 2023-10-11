import math 
import heapq
import itertools
import bisect
import random
import time
from collections import deque
import sys
from cmath import exp,pi
input=sys.stdin.readline

def f(left):
    if b*x<z:
        return left*x
    k=(left//b)*z
    left-=(left//b)*b
    return k+left*x

t=int(input())
for _ in range(t):
    n,a,b,x,y,z=map(int,input().split())
    ans=10**30
    if a*z<b*y:
        a,b,y,z=b,a,z,y
    if a*x<y:
        print(x*n)
    else:
        s=(n//a)*y
        k=(n//a)*a
        for i in range(b):
            ans=min(ans,s+f(n-k))
            s-=y
            k-=a
            if s<0:
                break
        print(ans)
            
            
                
    
        
