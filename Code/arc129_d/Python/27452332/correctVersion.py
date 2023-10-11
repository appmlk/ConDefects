import sys
input = sys.stdin.readline

N=int(input())
A=list(map(int,input().split()))

if sum(A)!=0:
    print(-1)
    exit()

S=[0]
for a in A:
    S.append(S[-1]+a)

x=sum(S)
if x%N!=0:
    print(-1)
    exit()

#print(x,S)

if x<0:
    ANS=-x//N
    A[0]+=ANS*2
    A[1]-=ANS
    A[-1]-=ANS

elif x>0:
    ANS=x//N
    A[-1]+=ANS*2
    A[0]-=ANS
    A[-2]-=ANS

C=A[:]

OK=10**17
NG=-1

while OK>NG+1:
    mid=(OK+NG)//2

    C[0]+=mid*2
    C[1]-=mid
    C[-1]-=mid

    C[-1]+=mid*2
    C[0]-=mid
    C[-2]-=mid

    S=[0]
    for a in C:
        S.append(S[-1]+a)

    C[0]-=mid*2
    C[1]+=mid
    C[-1]+=mid

    C[-1]-=mid*2
    C[0]+=mid
    C[-2]+=mid

    for i in range(len(S)-1):
        if S[i]<0:
            NG=mid
            break
        S[i+1]+=S[i]

    else:
        OK=mid


#print(OK)

ANS+=OK*2

A[0]+=OK*2
A[1]-=OK
A[-1]-=OK

A[-1]+=OK*2
A[0]-=OK
A[-2]-=OK

S=[0]
for a in A:
    S.append(S[-1]+a)

for i in range(len(S)-1):
    S[i+1]+=S[i]
    ANS+=S[i]

print(ANS)