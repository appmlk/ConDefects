n,k=map(int,input().split())
a=list(map(int,input().split()))
asort=sorted(a)

ak=[[] for _ in range(k)]
for i in range(n):
  ak[i%k].append(a[i])

aksort=[0]*k
for i in range(k):
  aksort[i] = sorted(ak[i])

for i in range(n):
  if asort[i] != aksort[i%k][i//k]:
    print("No")
    exit()
print('Yes')