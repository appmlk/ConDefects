S = input()
ans = [0] * len(S)
for i in range(len(S)):
  ans[i] = S[i]
print(*ans)