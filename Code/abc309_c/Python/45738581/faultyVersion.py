N, K = map(int, input().split())
arr = []
total = 0
for i in range(N):
  tmp1, tmp2 = map(int, input().split())
  arr.append([tmp1,tmp2])
  total += tmp2
arr.sort()

if total < K:
  print(1)
  exit()

i = 0
while 1:
  total -= arr[i][1]
  if total <= K:
    print(arr[i][0]+1)
    exit()
  i += 1