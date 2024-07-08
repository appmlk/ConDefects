N, X = map(int, input().split())
S = list(map(int, input().split()))
ans = 0
for i in range(N):
  if S[i] <= X:
    ans += S[i]
print(ans)