s=input()
ans=0
for i in range(len(s)):
    for j in range(i,len(s)):
        t=s[i:j+1]
        tr=t[::-1]
        if t==tr:
            ans=max(ans,len(t))
print(ans)