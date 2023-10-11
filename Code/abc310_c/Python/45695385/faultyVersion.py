n=int(input())
k=set([])
for i in range(n):
  s=input()
  S=[]
  for i in range(len(s)):
    S.append(s[i])
  t=""
  for i in range(len(s)):
    t+=S[len(s)-1-i]
  
  if not s in k:
    if len(s)==1:
      k.add(s)
      k.add(s+str(1))
    else:
      k.add(s)
      k.add(t)

print(int(len(k)/2))