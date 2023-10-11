n=int(input())
s=input()
t=input()

sl=[]
tl=[]

for i in range(n):
  sl.append(s[i])
  tl.append(t[i])
  
sl.sort()
tl.sort()

if sl!=tl:
  print('No')
  exit()

  
now=n-1
ans=0

for i in reversed(range(n)):
  while True:
    if s[i]==t[now]:
      ans+=1
      now-=1
      break
    now-=1
    if now<0:
      break
  if now<0:
    break
print(n-ans)
    
