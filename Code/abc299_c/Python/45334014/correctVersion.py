n=int(input())
s=input()
ok=False
cnt=0
ans=0
for i in range(n):
    if s[i]=="o":
        cnt+=1
    else:
        ok=True
        ans=max(ans,cnt)
        cnt=0
if ok:
    ans=max(ans,cnt)
print(ans if ans>0 else -1)