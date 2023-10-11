
a,b,c,d,e,f=map(int,input().split())
mod=998244353
a1=a%mod*b%mod*c%mod
a2=d%mod*e%mod*f%mod
print((a1-a2)%mod)