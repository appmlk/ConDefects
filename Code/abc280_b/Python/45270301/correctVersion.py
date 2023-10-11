N = int(input())
S = list(map(int, input().split()))
ans = [S[0]]

for i in range(1,N):
  ans.append(S[i]-S[i-1])

print(*ans)