N = int(input())
S = input()

ans = []
pre = '0'
bottom = ['A', 'B']
for i in range(N-1, -1, -1):
  if pre == S[i]:
    continue
  for j in range(i+1):
    ans.append(bottom[int(pre)])
  pre = S[i]

print(len(ans))
print(*ans)