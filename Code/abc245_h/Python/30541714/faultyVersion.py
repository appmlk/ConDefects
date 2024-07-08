mod=998244353

def comb(n,r):
  if r>n:
    return 0
  tmp1=1
  tmp2=1
  for i in range(r):
    tmp1*=n-i
    tmp2*=i+1
    tmp1%=mod
    tmp2%=mod
  return (tmp1*pow(tmp2,mod-2,mod)%mod)

def fact(n):
  if n==1:
    return []
  arr=[]
  temp=n
  for i in range(2,int(-(-n**0.5//1))+2):
    if temp%i==0:
      cnt=0
      while temp%i==0:
        cnt+=1
        temp//=i
      arr.append([i,cnt])
  if temp!=1:
    arr.append([temp,1])
  if arr==[]:
    arr.append([n,1])
  return arr

def pow_mod(p,k):
  if p%mod==0:
    return 0
  return pow(p,k,mod)

def calc(p,a,k,x):
  return comb(k+x-1,x)*pow_mod(p-1,k-1)%mod*pow_mod(p,(a-1)*(k-1))%mod

k,n,m=map(int,input().split())
if k==1:
  print(1)
  exit()
ans=1
f=fact(m)
for p,a in f:
  N=n%(p**a)
  if N!=0:
    x=0
    while N%p==0:
      x+=1
      N//=p
    ans*=calc(p,a,k,x)
    ans%=mod
  else:
    tmp=pow_mod(p**a,k)
    for i in range(a):
      tmp-=calc(p,a,k,i)*(p-1)%mod*pow_mod(p,a-i-1)
      tmp%=mod
    ans*=tmp
    ans%=mod

print(ans)