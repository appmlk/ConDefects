r=range
K=int(input().split()[1])
N=int(K**.5)
S=(K-N*N)//2
f=lambda a,b:[print(x,y,a-x-y)for x in r(b)for y in r(b)if x+y<=a and x+y+b>=a]
f(S-2,N)
f(S+N-1,N+1)
f(S+N+N-1,N)
if(K+N*N)%2:print(N,N,N)