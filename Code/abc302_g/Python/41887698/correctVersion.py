from itertools import *

N=int(input())
A=list(map(int, input().split()))
A=[a-1 for a in A]
B=list(sorted(A))

L=4
C=[0]*(L**2)
for a,b in zip(A,B):
    if a!=b:
        C[a*L+b]+=1

res=0
for p in permutations(range(4),2):
    x,y=p
    cnt=min(C[x*L+y],C[y*L+x])
    C[x*L+y]-=cnt
    C[y*L+x]-=cnt
    res+=cnt


for p in permutations(range(4),3):
    x,y,z=p
    cnt=min(C[x*L+y],C[y*L+z],C[z*L+x])
    C[x*L+y]-=cnt
    C[y*L+z]-=cnt
    C[z*L+x]-=cnt
    res+=cnt*2


res+=(sum(C)*3)//4
print(res)