n, a, b = map(int, input().split())
s=list(input())
stnum=s.count("(")
ans=0
pos=0
while stnum < n:
  while s[pos]=="(":
    pos=pos+1
  s[pos]="("
  stnum+=1
  ans+=b
pos=n*2-1
while stnum > n:
  while s[pos]==")":
    pos=pos-1
  s[pos]=")"
  stnum-=1
  ans+=b
cnts=0
for i in range(2*n):
  if s[i]=="(":
    cnts+=1
  elif s[i]==")" and cnts > 0:
    cnts-=1
amari=0
if cnts%2==1:
  amari=1
ans=ans+min((cnts//2+amari)*2*b,(cnts//2+amari)*a)
print(ans)