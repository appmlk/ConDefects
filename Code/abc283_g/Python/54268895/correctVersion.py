n,l,r=map(int,input().split())
a=list(map(int,input().split()))

e=[]

for i in a:
  now=i
  for j in e:
    now=min(now,now^j)
  if now:
    e.append(now)
    
e.sort(reverse=True)
    
for i in range(len(e)):
  now=1<<(e[i].bit_length()-1)
  for j in range(len(e)):
    if i==j:
      continue
    if e[j]&now:
      e[j]^=e[i]
  e.sort(reverse=True)  
    
e.sort()

def f(x):
  ans=0
  for i in range(len(e)):
    if x&1:
      ans^=e[i]
    x//=2
  return ans
  
print(*[f(i) for i in range(l-1,r)])