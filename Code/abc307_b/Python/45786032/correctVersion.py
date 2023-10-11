n,*s=open(0).read().split()
n=int(n)
for i in range(n):
  for j in range(n):
    if i!=j and s[i]+s[j]==(s[i]+s[j])[::-1]:
      print("Yes")
      exit()
print("No")