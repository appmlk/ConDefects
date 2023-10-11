n = int(input())
s = input()
sr=''.join(reversed(s))
ans=0
cnt=0
flag=0
for i in range(n):
    if s[i]=="-":
        if flag == 0:
            flag = 1
        else:
            ans=max(ans,cnt)
            cnt=0
    else:
        if flag == 1:
            cnt+=1
flag=0
cnt=0
for i in range(n):
    if sr[i]=="-":
        if flag == 0:
            flag = 1
        else:
            ans=max(ans,cnt)
            cnt=0
    else:
        if flag == 1:
            cnt+=1
if ans == 0:
    print(-1)
else:
    print(ans)
    