N = int(input())
A = [list(map(int, input().split())) for _ in range(N)]
B = [list(map(int, input().split())) for _ in range(N)]
A_t = A
l = 0

for i in range(4):
  #print(A_t)
  for j in range(N):
    for k in range(N):
      if (A_t[j][k] == 1) and (B[j][k] != 1):
          #print(A_t[j][k],B[j][k] )
          l += 1

  if l == 0:
    print('Yes')
    #print(B)
    #print(A_t)
    exit()
  A_t = []
  for x in zip(*A[::-1]):
    A_t.append(list(x))
  A = A_t

  l = 0
print('No')