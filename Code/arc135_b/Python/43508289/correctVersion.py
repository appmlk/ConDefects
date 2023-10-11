n=int(input())
s=list(map(int,input().split()))
mn=[0]*3
d=[0]*3
for i in range(n-1):
    d[i%3]=s[i+1]-s[i]+d[i%3]
    mn[i%3]=min(mn[i%3],d[i%3])
# print(mn)
if -sum(mn)>s[0]:
    print("No")
    exit()
a=[-mn[0],-mn[1]]
for i in range(n):
    a.append(s[i]-a[-1]-a[-2])
print("Yes")
print(*a)