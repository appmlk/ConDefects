s=list(input())
ans=0
for i in range(len(s)):
    for j in range(i+1,len(s)+1):
        if s[i:j]==list(reversed(s[i:j])):
            ans=max(j-i,ans)
print(ans)