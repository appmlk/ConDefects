n=int(input())
s=input()
a=[]
T=False
b=1
for i in range(n-1):
  if s[i]=='>':
    b+=1
  else:
    if b!=1:
      a.append(b)
      b=1
if b!=1:
  a.append(b)
c=0
for i in a:
  c+=(n*(n-1))//2
print(c)