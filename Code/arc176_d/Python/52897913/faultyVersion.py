import sys
input = sys.stdin.readline

mod=998244353

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

N,M=map(int,input().split())
P=list(map(int,input().split()))

if N==2:
    print(1)
    exit()

if N==3:
    A=[[2,1],[2,1]]

    POWA=[A]

    for i in range(60):
        POWA.append(prod(POWA[-1],POWA[-1],2,2,2)) # ベキを求めて


    n=M
    X=[0,0]
    X[0]+=abs(P[1]-P[0])+abs(P[2]-P[1])
    X[1]=4-X[0]

    X=[X]

    while n:
        X=prod(X,POWA[n.bit_length()-1],1,2,2) # n乗の場合
        n-=1<<(n.bit_length()-1)

    print(X[0])
    exit()
    

ALL=N*(N-1)//2

A=[[ALL-(N-2)*2,(N-2)*2,0],
   [1,ALL-1-(N-3),N-3],
   [0,4,ALL-4]
   ]

POWA=[A]

# 漸化式を行列累乗で求める（ダブリング）

for i in range(60):
    POWA.append(prod(POWA[-1],POWA[-1],3,3,3)) # ベキを求めて

X=[[1,0,0]]

n=M
while n:
    X=prod(X,POWA[n.bit_length()-1],1,3,3) # n乗の場合
    n-=1<<(n.bit_length()-1)

#print(X)

AS=0
for i in range(N):
    AS=AS+i*(i+1)//2

PX=[0]
for i in range(1,N+1):
    plus=(N-i)*(N-i+1)//2
    minus=(i-1)*(i-1+1)//2
    PX.append(plus+minus)
    

ANS=0
for i in range(N-1):
    a,b=P[i],P[i+1]
    ANS+=abs(b-a)*X[0][0]

    k=PX[a]-abs(b-a)+PX[b]-abs(b-a)
    ANS+=k*X[0][1]*pow((N-2)*2,mod-2,mod)%mod

    ANS+=(AS-abs(b-a)-k)*X[0][2]*pow(ALL-1-(N-2)*2,mod-2,mod)%mod

    ANS%=mod

print(ANS%mod)
    
    
    
