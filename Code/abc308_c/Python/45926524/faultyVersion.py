n=int(input())
p=[]
for i in range(n):
    a,b=map(int,input().split())
    p.append(((a*(10**100))/(a+b),i+1))
p.sort(reverse=True)

l=[]
i=0
while i<n:
    if i<n-1 and p[i][0]==p[i+1][0]:
        same_n=2
        same_p=p[i][0]
        j=2
        while i+j<n and p[i+j][0]==same_p:
            same_n+=1
            j+=1
            if i+j==n:
                break
        j-=1
        while j>=0:
            l.append(p[i+j][1])
            j-=1
        i+=same_n
    else:
        l.append(p[i][1])
        i+=1

print(*l,sep=' ')