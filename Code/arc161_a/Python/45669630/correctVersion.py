N=int(input())
A=list(map(int,input().split()))
A.sort()
if N==1:
    print("Yes")
    exit()
B=[0]*N
for i in range(N):
    if i<=N//2:
        B[2*i]=A[i]
    else:
        B[2*i-N]=A[i]
for i in range(N):
    if i%2==0:
        continue
    if B[i-1]<B[i] and B[i]>B[i+1]:
        continue
    else:
        print("No")
        break
else:
    print("Yes")