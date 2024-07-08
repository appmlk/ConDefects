# -*- coding: utf-8 -*-

T=int(input())

for i in range(T):
    A=list(map(int,input().split()))
    P=list(map(int,input().split()))

    K=A[0]*-2+A[1]*-1+A[3]+A[4]*2

    ans=0
    if K<0:
        if P[3]<P[4]/2:
            ans=P[3]*abs(K)
        else:
            ans=P[4]*abs(K)//2
            if K%2!=0:
                ans+=min(P[3],P[4])

    print(ans)