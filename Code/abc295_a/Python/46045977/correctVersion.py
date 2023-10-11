n=int(input())
L=list(map(str,input().split()))
ans="No"
for i in range(len(L)):
  if L[i]=="and" or L[i]=="not" or L[i]=="that" or L[i]=="the" or L[i]=="you":
    ans="Yes"
    break
print(ans)