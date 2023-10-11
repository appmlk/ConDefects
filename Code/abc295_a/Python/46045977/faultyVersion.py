n=int(input())
L=list(map(str,input().split()))
ans="No"
for i in range(len(L)):
  if L[i]=="and" or L[i]=="not" and L[i]=="that" and L[i]=="the" and L[i]=="you":
    ans="Yes"
    break
print(ans)