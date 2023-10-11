import math
# import heapq
import sys
sys.setrecursionlimit(10**6)
# n,m,k = map(int, input().split())
# n = int(input())
n,q = map(int, input().split())
if q==2:
    print(int(math.log(n,2))+1)
    exit()
P = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
r = 0
while q!=P[r]:
    r+=1
l = (r+1)//2
P = P[:r+1]
for i in range(0,l,2):
    c = P[i]
    P[i] = P[r-i]
    P[r-i] = c
p,pp = P[0:l],P[l:]
p.sort()
pp.sort()

def rep(k,c,a,b):
    for i in range(k,len(a)):
        s = c*a[i]
        if s<=n:
            b.append(s)
            rep(i,s,a,b)
        else:
            break
l,ll = [],[]
rep(0,1,p,l)
rep(0,1,pp,ll)
ll.sort()
ans=len(ll)+1
for x in l:
    lo,hi=-1,len(ll)+1
    s=0
    while lo+1<hi:
        mid = (lo+hi)//2
        # print(x,mid)
        if x*ll[mid]<=n:
            s=mid+1
            lo=mid
        else:
            hi=mid
    # print(x,s)
    ans+=s+1
print(ans)
