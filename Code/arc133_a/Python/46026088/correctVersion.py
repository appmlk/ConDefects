n = int(input())
A = list(map(int,input().split()))
if n==1:
    print("")
    exit()

for i in range(n-1):
    if A[i] > A[i+1]:
        x = A[i]
        break
else:
    x = A[i+1]

print(*[a for a in A if a!=x])