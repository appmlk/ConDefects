N = int(input())
A = list(map(int,input().split()))
sA = sum(A)
one = sA%N
zero = N-one
for i in range(N):
    A[i]-=sA//N
#one個の1、残り全部0
A_sum = [0]
for i in range(N):
    A_sum.append(A_sum[-1]+A[i])
last = [10**16 for i in range(N)]
last[0]=0
for i in range(1,1+N):
    for j in reversed(range(N)):
        #最後がjの場合
        if j==0:
            last[j]=last[0]+abs(A_sum[i]-j)
        else:
            last[j] = min(last[j-1],last[j])+abs(A_sum[i]-j)
print(last[one])