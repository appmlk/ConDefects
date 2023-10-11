n = int(input())
a = [0]+list(map(int, input().split()))
e = [0]*(n+2)
rui = [0]*(n+2)
mod = 998244353
#e[n]=0
for i in reversed(range(1,n)):
  A = a[i]
  e[i]=(rui[i+1]-rui[i+A+1]+(A+1))*pow(A,mod-2,mod)
  e[i]%=mod
  rui[i]=e[i]+rui[i+1]
  rui[i]%=mod
print(e[1])