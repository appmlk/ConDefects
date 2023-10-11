N,M = map(int,input().split())
ans = 'Yes'
B = []
for i in range(N):
  b = list(map(int,input().split()))
  for j in range(M-1):
    if ((b[j+1] - 1) % 7) - ((b[j] - 1) % 7) != 1:
      ans = 'No'
    if b[j+1] - b[j] != 1:
      ans = 'No'
  B.append(b)

for i in range(N-1):
  if B[i+1][0] - B[i][0] != 7:
    ans = 'No'
print(ans)