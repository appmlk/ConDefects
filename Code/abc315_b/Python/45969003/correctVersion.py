m = int(input())
d = list(map(int, input().split()))

mid = (sum(d) + 1) // 2

i = 0
while True:
  if mid - d[i] > 0 and m != 1:
    mid -= d[i]
    i += 1
  else:
    print(i+1, mid)
    break