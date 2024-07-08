M=998244353
n,m,k=map(int,input().split())
c=[0]*(m+1)
for a in list(map(int,input().split())):
  c[a]+=1
z=c[0]
c[0]=0

fa=[1]
fb=[1]
for i in range(1,z+1):
  fa.append((fa[-1]*i)%M)
  fb.append((fb[-1]*pow(i,M-2,M))%M)

for i in range(m):
  c[i+1]+=c[i]
f=0
for i in range(1,m+1):
  if c[i-1]>k-1:
    break
  g=0
  for j in range(z+1):
    if c[i-1]+j>k-1:
      break
    g+=fa[z]*fb[j]*fb[z-j]*pow(i-1,j,M)*pow(m-i+1,z-j,M)
    g%=M
  f+=g
  f%=M
print((f*pow(pow(m,z,M-1),M-2,M))%M)