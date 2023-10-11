n=int(input())
a=list(map(int,input().split()))
t=[]
for i in a:
    if len(t)==0 or t[-1][0]!=i:
        t.append([i,1])
    else:
        t[-1][1]+=1
mx0=0
mx1=0
c0=0
c1=1
ind0=0
ind1=0
for i in range(n):
    c0=max(c0,0)
    c1=max(c1,0)
    if a[i]==0:
        c0+=1
        c1-=1
    else:
        c1+=1
        c0-=1
    mx0=max(mx0,c0)
    mx1=max(mx1,c1)
print(mx0+mx1+1)