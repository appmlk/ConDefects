# abc312c_InvisibleHand.py 

from bisect import bisect_left,bisect_right

N,M = map(int,input().split())
A = list(map(int,input().split()))
B = list(map(int,input().split()))
A.sort()
B.sort()

low = 0
high = 10**9
while low +1 < high:
    m = (high+low)//2
    fx = bisect_right(A,m)
    gx = M - bisect_left(B,m)
    if fx >= gx: high = m
    else: low = m

print(high)