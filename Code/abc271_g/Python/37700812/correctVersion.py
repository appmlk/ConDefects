import numpy as np
N,X,Y=map(int,input().split())
C=input()
Mod=998244353
def ABmodC(A,B,C):
    b=bin(B)[2:]
    a=[A]
    for i in range(len(b)-1):
        a.append((a[-1]**2)%C)
    d=1
    for i in range(len(b)):
        if b[-i-1]=='1':
            d=(d*a[i])%C
    return d

Xa=(X*ABmodC(100,Mod-2,Mod))%Mod
Ya=(Y*ABmodC(100,Mod-2,Mod))%Mod

q=1
for i in range(24):
    if C[i]=='T':
        q=(q*(1-Xa))%Mod
    elif C[i]=='A':
        q=(q*(1-Ya))%Mod

D=np.zeros((24,24),dtype='int64')

for i in range(24):
    if C[i]=='T':
        D[i,(i-1)%24]=Xa
    elif C[i]=='A':
        D[i,(i-1)%24]=Ya
    for ii in range(1,24):
        if C[(i-ii)%24]=='T':
            D[i,(i-1-ii)%24]=(D[i,(i-ii)%24]*((1-Xa)%Mod))%Mod
        elif C[(i-ii)%24]=='A':
            D[i,(i-1-ii)%24]=(D[i,(i-ii)%24]*((1-Ya)%Mod))%Mod

D=D*ABmodC((1-q)%Mod,Mod-2,Mod)
D=D%Mod

Ans=np.zeros((24,1),dtype='int64')
Ans[23,0]=1
def mult(A,B):
    if A.shape[1]!=B.shape[0]:
        return 0
    else:
        C=np.zeros((A.shape[0],B.shape[1]),dtype='int64')
        for i in range(C.shape[0]):
            for ii in range(C.shape[1]):
                for iii in range(A.shape[1]):
                    C[i,ii]=(C[i,ii]+A[i,iii]*B[iii,ii])%Mod
        return C

def npABmodC(A,B,C):
    #Aは正方行列
    #numpy(np)がimportされていること
    b=bin(B)[2:]
    a=[A]
    for i in range(len(b)-1):
        a.append(mult(a[-1],a[-1])%C)
    d=np.identity(A.shape[0],dtype='int64')
    for i in range(len(b)):
        if b[-i-1]=='1':
            d=mult(d,a[i])%C
    return d

ans=0
Ans=mult(npABmodC(D,N,Mod),Ans)
for i in range(24):
    if C[i]=='A':
        ans=(ans+Ans[i,0])%Mod

print(int(ans)%Mod)