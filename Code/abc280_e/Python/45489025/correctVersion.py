mod=998244353
inv=pow(100,mod-2,mod)
N,P=map(int,input().split())
dp=[0,1]
for i in range(N-1):
    dp.append(1+dp[-1]*(100-P)*inv+dp[-2]*P*inv)
    dp[-1]%=mod
print(dp[-1])
#print(dp)