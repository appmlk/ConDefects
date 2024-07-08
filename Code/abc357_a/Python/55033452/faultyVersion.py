N, M = map(int, input().split())
H = list(map(int, input().split()))
ans = 0
sum = 0
i = 0
while i < N:
  sum += H[i]
  if sum > M:
    print(i)
    break
  i += 1