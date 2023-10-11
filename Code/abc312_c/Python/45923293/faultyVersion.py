N,M=map(int,input().split())
A=list(map(int,input().split()))
B=list(map(int,input().split()))

from bisect import bisect_right
from bisect import bisect_left
A.sort()
B.sort()
l=0
r=10**9
while l<r:
    m=(l+r)//2
    a=bisect_right(A,m)
    b=M-bisect_left(B,m)
    if a>=b:
        r=m
    else:
        l=m+1
print(l)