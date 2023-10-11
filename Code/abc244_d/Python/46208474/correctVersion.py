S=list(input().split())
T=list(input().split())
count=0
for i in range(3):
  if S[i]!=T[i]:
    count+=1
print("Yes") if count!=2 else print("No")