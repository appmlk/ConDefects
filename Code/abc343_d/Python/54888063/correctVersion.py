n,t=map(int,input().split())
s=[0 for _ in range(n)]
p={}
p[0]=n
for i in range(t):
    a,b=map(int,input().split())
    a-=1
    p[s[a]]-=1
    if p[s[a]]==0:
        del p[s[a]]
    s[a]+=b
    if s[a] not in p:
        p[s[a]]=0
    p[s[a]]+=1
    print(len(p))