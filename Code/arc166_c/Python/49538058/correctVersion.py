p=998244353;f=[1,1];e=[1];N=1<<21;R=range
for i in R(N):f+=(f[-1]+f[-2])%p,
for i in R(2,N,2):e+=e[-1]*f[i]**2%p,
for i in[*open(0)][1:]:h,w=sorted(map(int,i.split()));print(e[h]*pow(f[h*2+1],w-h,p)%p)