N,M,P = map(int,input().split())
count = 0
while M < N:
  count += 1
  M += P
print(count)

