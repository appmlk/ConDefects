N,M,P = map(int,input().split())
count = 0
while M <= N:
  M +=P
  count+=1
print(count)