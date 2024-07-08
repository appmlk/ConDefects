def Divisors(N):
    divisors=[]
    for i in range(1,N+1):
        if i**2>=N:
            break
        elif N%i==0:
            divisors.append(i)
    if i**2==N:
        divisors+=[i]+[N//i for i in divisors[::-1]]
    else:
        divisors+=[N//i for i in divisors[::-1]]
    return divisors

import random
N=int(input())
A=list(map(int,input().split()))
ans=-1
for _ in range(100):
    i=random.randint(0,N-1)
    j=random.randint(0,N-1)
    if i==j:
        continue
    for M in Divisors(abs(A[i]-A[j])):
        if M<=2:
            continue
        B=[a%M for a in A]
        B.sort()
        bound=[0]
        for i in range(1,N):
            if B[i-1]!=B[i]:
                bound.append(i)
        bound.append(N)
        for l,r in zip(bound,bound[1:]):
            if (r-l)*2>N:
                ans=M
    if ans!=-1:
        break
print(ans)