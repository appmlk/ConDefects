N = int(input())

A = []
for i in range(N):
  query, num = map(int,input().split())
  if query == 1:
    A.append(num)
  elif query == 2:
    print(A[-1])
    