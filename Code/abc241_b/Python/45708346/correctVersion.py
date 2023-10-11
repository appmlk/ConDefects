n,m=list(map(int,input().split()))
l=[int(x) for x in input().split()]
d=dict()
for i in l:
    if i in d:
        d[i]+=1
    else :
        d[i]=1
yes=True
nl=[int(x) for x in input().split()]
for i in nl:
    if i not in d or d[i]==0:
        yes=False
        break
    else:
      d[i]-=1
print("Yes"if yes else "No")