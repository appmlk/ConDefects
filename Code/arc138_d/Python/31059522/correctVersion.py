N,K=map(int, input().split())
if K!=1 and (K%2==0 or N==K):
    print("No")
    exit()
print("Yes")
def calc(bit,n,rets):
    if n==-1:
        rets.append(bit)
        return bit
    bit=calc(bit,n-1,rets)
    bit^=1<<n
    return calc(bit,n-1,rets)
gray=[]
calc(0,N-1,gray)
norm=[(1<<K)-1]
for i in range(K-1):
    norm.append((1<<K+1)-1-(1<<i))
for i in range(K, N):
    norm.append((1<<K-1)-1+(1<<i))
rets=[]
for g in gray:
    ret=0
    for i in range(N):
        if 1<<i&g!=0:
            ret^=norm[i]
    rets.append(ret)
print(*rets)
