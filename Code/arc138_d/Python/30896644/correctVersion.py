import sys
input = sys.stdin.readline
import random

N,K=map(int,input().split())

if (N>=2 and N==K) or K%2==0:
    print("No")
    exit()

A=0
B=1

check=[0]*(1<<N)

ANS=[]
check[A]=1
check[B]=1

def route(x,i,j):#xから始まるルート,bit iは固定.j 残りbitの個数
    NOW=x
    ANS.append(NOW)
    check[NOW]=1

    for k in range(1<<j):
        for l in range(N):
            if check[NOW ^ (1<<l)]==0 and l!=i:
                NOW=NOW ^ (1<<l)
                check[NOW]=1
                ANS.append(NOW)
                break

    return NOW^(1<<i)

checkbit=[0]*N     
for i in range(N-1,-1,-1):
    for j in range(N):
        if (1<<j)&A!=(1<<j)&B and checkbit[j]==0:
            break
    A=route(A,j,i)
    checkbit[j]=1

ANS.append(B)

# xorの掃き出し法・基底

BASE=[]

def sweep(x):
    for b in BASE:
        if b^x<x:
            x=b^x
    return x

BASE2=[]
LIST=list(range(N))
while len(BASE)<N:
    random.shuffle(LIST)
    c=0
    for i in range(K):
        c|=(1<<LIST[i])
    k=sweep(c)
    if k!=0:
        BASE.append(k)
        BASE2.append(c)

DICT={i:BASE2[i] for i in range(N)}

ANS2=[0]

for i in range(1,len(ANS)):
    b=ANS[i]^ANS[i-1]

    for i in range(20):
        if 1<<i==b:
            x=i
            break
        
    ANS2.append(ANS2[-1]^DICT[x])


if len(ANS2)!=len(set(ANS2)):
    print("No")
else:
    print("Yes")
    print(*ANS2)
    
