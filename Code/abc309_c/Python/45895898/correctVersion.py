# python3 answer.py < input.txt
# =list(map(int, input().split()))
#=int(input())
#import collections
#import itertools
#import numpy
#from collections import deque
#import heapq
#inf=10**100
#isok=True
#import numpy as np
#np.set_printoptions(suppress=True)
#k=int(input())

#n=int(input())
#lis=[]
n,k=list(map(int, input().split()))


def cal(x,li):
    res=0
    for i in range(n):
        if li[i][0]>=x:
            res+=li[i][1]
    #print(res>k)
    return res>k

maxday=0
lis=[]
for i in range(n):
    a,b=list(map(int, input().split()))
    maxday=max(maxday,a)
    lis.append([a,b])

l=-1
r=maxday+1

while abs(r-l)>1:
    mid=(l+r)//2
    #print(l,r,mid)
    if cal(mid,lis):#飲む量が多いか
        l=mid
    else:
        r=mid

if r<=0:
    print(1)
else:
    print(r)


