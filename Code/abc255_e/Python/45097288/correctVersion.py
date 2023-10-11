from collections import Counter

N,M=map(int,input().split())
S=list(map(int,input().split()))
X=list(map(int,input().split()))
C=Counter()
A=[-10**18]
for i in range(M):
    C[abs(A[0]-X[i])]+=1

for i in range(N-1):
    A.append(S[i]-A[i])
    for j in range(M):
        C[abs(A[i+1]-X[j])]+=1

ans=0
for key,value in C.items():
    ans=max(ans,value)

print(ans)