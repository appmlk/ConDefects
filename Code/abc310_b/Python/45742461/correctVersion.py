n,m=map(int,input().split())
s=[]
for i in range(n):
  p,c,*f=map(int,input().split())
  s.append([p,c,f])
for i in range(n):
  for j in range(n):
    if i!=j and s[i][0]>=s[j][0] and set(s[j][2])>=set(s[i][2]) \
            and (s[i][0]>s[j][0] or set(s[j][2])-set(s[i][2])):
      print("Yes")
      exit()
print("No")