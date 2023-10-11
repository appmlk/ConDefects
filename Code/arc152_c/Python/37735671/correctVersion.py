from functools import reduce; from math import gcd
N=int(input());A=list(map(int,input().split()))
B = [2*A[i+1]-2*A[i] for i in range(N-1)];g=reduce(gcd,B)
mi = A[-1]-A[0]+A[0]%g
#print(mi)
for a in A:
    mi = min(mi,A[-1]-A[0]+(-A[-1]+2*a)%g)
    #print(mi)
print(mi)