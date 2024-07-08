n, m = map(int, input().split())
A = list(map(int, input().split()))
S = [input() for i in range(n)]
score = [i+1 for i in range(n)]

B = [(i,j) for i,j in zip(range(m), A)]
B.sort(key=lambda x:x[1], reverse=True)
C = [i for i,j in B]
# print(C)

for i in range(n):
  for j in range(m):
    if S[i][j] == "o":
      score[i] += A[j]
# print(score)
ms = max(score)

for i in range(n):
  count = 0
  for j in range(m):
    if ms <= score[i]:
      break
    index = C[j]
    if S[i][j] != "o":
      score[i] += A[index]
      count += 1
  print(count)
