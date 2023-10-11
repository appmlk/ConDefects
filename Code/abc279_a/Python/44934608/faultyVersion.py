s=list(map(str,input()))
ans=0
for i in range(len(s)):
  if s[i]=="W":
    ans+=2
  else:
    ans+=1
print(ans)