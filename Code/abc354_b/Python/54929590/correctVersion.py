N=int(input())
S, C = [""]*N, [0]*N
for i in range(N):
  S[i],C[i] = input().split()
  C[i] = int(C[i])
S.sort()
print(S[sum(C) % N])