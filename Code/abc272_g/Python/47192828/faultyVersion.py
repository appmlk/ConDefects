import math
from heapq import heapify, heappop, heappush
# import bisect
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)
def ipt(num=0):
    if num==1:return input().rstrip()
    return list(map(int, input().split()))
mod = 998244353
# d = [[0]*s2 for t in range(s1)]
# for i in range():
n=ipt()[0]
a=ipt()
h=(n+1)//2
import random
for i in range(30):
    s=random.randint(0,n-1)
    t=random.randint(0,n-1)
    u=abs(a[s]-a[t])
    x=1
    while x*x<=u:
        if u%x==0:
            c=0
            if x>=3:
                for t in range(n):
                    if abs(a[s]-a[t])%x==0:
                        c+=1
                        if c>=h:print(x);exit()
            c=0
            if u//x>=3:
                d=u//x
                for t in range(n):
                    if abs(a[s]-a[t])%d==0:
                        c+=1
                        if c>=h:print(d);exit()

        x+=1
print(-1)
