def factor(x):
    d=[]
    e=[]
    for i in range(1,x+1):
        if i*i>x:
            break
        if i*i==x:
            d.append(i)
            break
        if x%i==0:
            d.append(i)
            e.append(x//i)
    return d+e[::-1]

def make_prime(N): #O(NloglogN)
    Plist=[]
    L=[0]*(N+1)
    for i in range(2,N+1):
        if L[i]==0:
            Plist.append(i)
            for j in range(i,N+1,i):
                L[j]=1
    return Plist

Plist=make_prime(10000)

from collections import *
def prime(N): #O(sqrt(N))
    P=defaultdict(int)
    for i in Plist:
        if i*i>N:
            break
        if N%i==0:
            j=0
            while N%i==0:
                N//=i
                j+=1
            P[i]=j
        i+=1
    if N!=1:
        P[N]=1
    return P

T=int(input())

for _ in range(T):
    K=int(input())
    if K%4==0 or K%5==0:
        print(-1)
        continue
    if K%2==0:
        K//=2
    pk=prime(K)
    pk[3]+=2
    l=9*K
    for p in pk:
        l=l*(p-1)//p
    for i in factor(l):
        if pow(10,i,9*K)==1:
            print(i)
            break