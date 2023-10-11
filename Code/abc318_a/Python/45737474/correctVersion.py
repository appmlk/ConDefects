n,m,p = input().split()
n,m,p = int(n),int(m),int(p)
count = 0
for i in range(n):
  temp = m + (i*p)
  if (temp<=n):
    count+=1
print(count)