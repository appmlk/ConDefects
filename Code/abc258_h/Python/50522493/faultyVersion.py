M=998244353
n,s=map(int,input().split())
f=lambda a,b,c,d:[(a*c+b*d*5)%M,(a*d+b*c)%M]
CD=[cd:=[M//2+1]*2]+[cd:=f(*cd,*cd)for i in range(60)]
g=1
k=0
a=[0,*map(int,input().split()),s]
for i in range(1,n+2):
    x,y=0,598946612
    r=a[i]-a[i-1]-1
    for j in range(60):
        if r&1:x,y=f(x,y,*CD[j])
        r>>=1
    c,d=x*2%M,f(x,y,*CD[0])[0]*2%M
    b=(d-c)%M
    g,k=(c*g+b*k)%M,(d*g+c*k)%M
print(g if s&1 else k)