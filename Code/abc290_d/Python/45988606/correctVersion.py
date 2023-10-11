import math
T=int(input())

for _ in range(T):
    N,D,K=map(int,input().split())
    gcd=math.gcd(N,D)
    if gcd==1:
        answer=(D*(K-1))%N
    else:
        d=D//gcd
        n=N//gcd
        answer=((((K-1)%n)*d%N)*gcd+(K-1)//n)%N
    print(answer)