a=list(map(int,input().split()))
ans=[]
for i in range(13):
    i+=1
    s=a.count(i)
    if s==0:
        continue
    ans.append(s)
if (len(ans)==2)and((ans[0]==3 and ans[1]==2)or(ans[0]==2 and ans[1]==3)):
    print("Yes")
else:
    print("No")