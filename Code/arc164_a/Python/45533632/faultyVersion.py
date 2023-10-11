#ARC164A

def I(): return int(input())
def LI(): return list(map(int,input().split()))
def RI(): return map(int,input().split())
def DI(): return list(input())

def change(N,shinsu):
    keta=0
    for i in range(10**9):
        if N<shinsu**i:
             keta+=i
             break
    ans=[0]*keta
    check=0
    for i in range(1,keta+1):
        j=N//(shinsu**(keta-i))
        ans[check]=j
        check+=1
        N-=(j)*(shinsu**(keta-i))
    return ans

t=I()
for _ in range(t):
    n,k=RI()
    nhen=change(n,3)
    #print(sum(nhen))
    if (k-sum(nhen))%2==0:
        print('Yes')
    else:
        print('No')