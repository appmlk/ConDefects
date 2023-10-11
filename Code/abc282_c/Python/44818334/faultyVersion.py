n=int(input())
s=input()
s=list(s)
cnt=0
for i in range(n):
    if s[i]=='"':
        cnt+=1
    if s[i]==",":
        if cnt%2==1:
            s[i]="."
ans=""
for i in range(n):
    ans+=s[i]
print(ans)