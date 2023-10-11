n,m,t=map(int,input().split())
s=list(map(int,input().split()))
dic=dict()
for i in range(m):
    a,b=map(int,input().split())
    dic[a]=b

for now in range(1,n):
    
    if t-s[now-1]<0:
        print("No")
        exit()
    else:
        t-=s[now-1]
    if now+1 in dic:
        t+=dic[now+1]
    
print("Yes")