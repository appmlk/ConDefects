import sys
input = sys.stdin.readline

mod=998244353

N,S=map(int,input().split())
A=sorted(map(int,input().split()))

if 1 in A:
  DP=[1,0]
else:
  DP=[1,1]

DP=[DP]

# 行列の計算（numpyを使えないとき,modを使用）
def prod(A,B,k,l,m):# A:k*l,B:l*m
    C=[[None for i in range(m)] for j in range(k)]

    for i in range(k):
        for j in range(m):
            ANS=0
            for pl in range(l):
                ANS=(ANS+A[i][pl]*B[pl][j])%mod

            C[i][j]=ANS

    return C

def plus(A,B,k,l):# a,B:k*l
    C=[[None for i in range(l)] for j in range(k)]

    for i in range(k):
        for j in range(l):
            C[i][j]=(A[i][j]+B[i][j])%mod

    return C

# 漸化式を行列累乗で求める（ダブリング）


POWA=[[[1,1],[1,2]]]

for i in range(60):
    POWA.append(prod(POWA[-1],POWA[-1],2,2,2)) # ベキを求めて


now=0
ind=0
if A[ind]==1:
    ind+=1

while ind<len(A):
    #print(now,DP)
    a=A[ind]

    if a%2==0 and ind+1<len(A) and A[ind+1]==a+1:
        X=DP
        n=(a-now)//2-1

        while n:
            X=prod(X,POWA[n.bit_length()-1],1,2,2) # n乗の場合
            n-=1<<(n.bit_length()-1)

        DP=X
        ind+=2
        now=a

    elif a%2==0:
        X=DP
        n=(a-now)//2-1

        while n:
            X=prod(X,POWA[n.bit_length()-1],1,2,2) # n乗の場合
            n-=1<<(n.bit_length()-1)

        DP=X
        DP[0][1]=DP[0][0]+DP[0][1]
        now=a
        ind+=1

    elif a%2==1:
        X=DP
        n=(a-now)//2-1

        while n:
            X=prod(X,POWA[n.bit_length()-1],1,2,2) # n乗の場合
            n-=1<<(n.bit_length()-1)

        DP=X
        DP[0][0]=DP[0][0]+DP[0][1]
        now=a-1
        ind+=1

#print(now,DP)

if S==now+1:
    print(DP[0][0])
    exit()

n=(S-now)//2-1
X=DP

#print(n)

while n:
    X=prod(X,POWA[n.bit_length()-1],1,2,2) # n乗の場合
    n-=1<<(n.bit_length()-1)

DP=X
#print(now,DP)

MINUS=DP[0][0]


X=DP
n=1
while n:
    X=prod(X,POWA[n.bit_length()-1],1,2,2) # n乗の場合
    n-=1<<(n.bit_length()-1)

DP=X

if S%2==1:
    print(DP[0][0])
else:
    print(DP[0][0]-MINUS)