M = int(input())
D = list(map(int, input().split()))

middleD = (sum(D)+1)/2
sumD = 0
for i in range(M):
  sumD += D[i]
  if sumD >= middleD:
    print(i+1, middleD - sumD + D[i])
    break