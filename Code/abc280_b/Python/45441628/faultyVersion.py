N = int(input())
S = list(map(int,input().split()))
A = [S[0]]
for i in range(1,N):
  a = S[i] - A[-1]
  A.append(a)
print(*A)