



n=int(input())
a=[0]+list(map(int,input().split()))



#################################################
import sys
sys.setrecursionlimit(10 ** 9)  # 再帰の上限をあげる
#########
uni_num=n+1
#########
union_root = [-1 for i in range(uni_num + 1)]  # 自分が親ならグループの人数のマイナス倍を、そうでないなら（元）親の番号を示す
union_depth = [0] * (uni_num + 1)


def find(x):  # 親は誰？
    if union_root[x] < 0:
        return x
    else:
        union_root[x] = find(union_root[x])
        return union_root[x]


def unite(x, y):
    x = find(x)
    y = find(y)
    if x == y:
        return
    if union_depth[x] < union_depth[y]:
        x, y = y, x
    if union_depth[x] == union_depth[y]:
        union_depth[x] += 1
    union_root[x] += union_root[y]
    union_root[y] = x

################################

mod=998244353
for i in range(1,n+1):
    if a[i]!=-1:unite(i,a[i])

miti=[0]*(n+2)
for i in range(1,n+1):
    if a[i]==-1:miti[find(i)]+=1




#############################
#############
cnb_max=10**5
#############

kai=[1]*(cnb_max+1)
rkai=[1]*(cnb_max+1)
for i in range(cnb_max):
    kai[i+1]=kai[i]*(i+1)%mod

rkai[cnb_max]=pow(kai[cnb_max],mod-2,mod)
for i in range(cnb_max):
    rkai[cnb_max-1-i]=rkai[cnb_max-i]*(cnb_max-i)%mod

def cnb(x,y):
    if y>x:
        return 0
    if x<0:return 0
    if y<0:return 0
    return (kai[x]*rkai[y]%mod)*rkai[x-y]%mod


def inv(n):
    return kai[n-1]*rkai[n]%mod

##################################




x=[]
cnt=0
for i in range(1,n+1):
    if i!=find(i):continue
    if miti[i]==1:x.append(-union_root[i])
    else:cnt+=1

m=len(x)
x=[0]+x
ans=cnt*pow(n,m,mod)%mod

dp=[[0]*(m+3) for i in range(m+3)]
dp[0][0]=1
for i in range(1,m+1):
    for j in range(i+1):
        dp[i][j]=dp[i-1][j]+dp[i-1][j-1]*x[i]%mod
        dp[i][j]%=mod
for j in range(1,m+1):
    ans+=(kai[j-1]*dp[m][j]%mod)*pow(n,m-j,mod)%mod
print(ans%mod)







