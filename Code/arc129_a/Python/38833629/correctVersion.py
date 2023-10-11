n,l,r = map(int,input().split())

z=format(n,'b')
z=int(z)
s=list(str(z))
m=len(s)
ans=0

for i in range(m):
    if n>>i&1 == 1:
        if 2**i<=l<r<2**(i+1):
           ans+=r-l+1
        elif 2**i<l<2**(i+1):
            ans+=2**(i+1)-l
        elif 2**i<=r<2**(i+1):
            ans+=r-2**i+1
        elif l<=2**i<2**(i+1)<r:
            ans+=2**i
            
print(ans)