# -*- coding: utf-8 -*-

N,A,B=map(int,input().split())
S=input()

ans=0
X=[0]*(N*2+1)
for i in range(N*2):
    if S[i]=="(":
        X[i+1]=1
    if S[i]==")":
        X[i+1]=-1

K=sum(X)
#print(X)
#print(K)
ans+=B*abs(K)//2
if K>0:
    for i in range(N*2):
        if X[N*2-i]==1:
            X[N*2-i]=-1
            K-=2
            if K==0: break

if K<0:
    for i in range(N*2):
        if X[i+1]==-1:
            X[i+1]=1
            K-=2
            if K==0: break

#print(X)
for i in range(N*2):
    X[i+1]+=X[i]

#print(X)
M=abs(min(X))
M+=M%2
#print(M)
ans+=min(A*M//2,2*B*M//2)
print(ans)
