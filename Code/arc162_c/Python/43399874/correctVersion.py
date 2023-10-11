import math
import heapq
import itertools
import bisect
import random
import time
from collections import deque
import sys
from cmath import exp,pi
from functools import cmp_to_key
input=sys.stdin.readline

def check(line):
    line.sort()
    p=0
    cnt=0
    for i in line:
        if p==i:
            p+=1
    if p==k:
        return 0
    if p<k:
        p+=1
        for i in line:
            if p==i:
                p+=1
        if p==k:
            return 1
    return -1

t=int(input())
for _ in range(t):
    n,k=map(int,input().split())
    graph=[[] for _ in range(n+1)]
    p=list(map(int,input().split()))
    for i in range(n-1):
        graph[p[i]].append(i+2)
    ans='Bob'
    arr=list(map(int,input().split()))
    for i in range(1,n+1):
        line=[]
        queue=deque([i])
        cnt=0
        while queue:
            a=queue.popleft()
            if arr[a-1]==-1:
                cnt+=1
            line.append(arr[a-1])
            for j in graph[a]:
                queue.append(j)
        x=check(line)
        if (x==0 and cnt<=1) or (x==1 and cnt==1):
            ans='Alice'
    print(ans)
        
    
    
    


    
