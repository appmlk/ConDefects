n,a,b=map(int,input().split())
if a>b:
  a,b=b,a
ax,bx=a,b
while ax>0:
  ax,bx=bx%ax,ax
g=bx
ans=0
const=a*b//g
rui=n//const
za=n%const
ans+=(rui*const*2+za+1)*za//2-(rui*const*2+za//a*a+a)*(za//a)//2-(rui*const*2+za//b*b+b)*(za//b)//2
ans+=rui*const*(const+1)//2*(const-const//a-const//b+1)
ans+=rui*(const*(const+1)//2-const*(1+const//a)//2-const*(1+const//b)//2+const)
print(ans)