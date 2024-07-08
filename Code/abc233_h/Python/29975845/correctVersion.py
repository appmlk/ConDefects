class BIT:
    n=1
    data=[0 for i in range(n)]
    def __init__(self,N):
        self.n=N
        self.data=[0 for i in range(N)]
    def add(self,p,x):
        assert 0<=p<self.n,"0<=p<n,p={0},n={1}".format(p,self.n)
        p+=1
        while(p<=self.n):
            self.data[p-1]+=x
            p+=p& -p
    def sum(self,l,r):
        # assert (0<=l and l<=r and r<=self.n),"0<=l<=r<=n,l={0},r={1},n={2}".format(l,r,self.n)
        return self.sum0(r)-self.sum0(l)
    def sum0(self,r):
        s=0
        while(r>0):
            s+=self.data[r-1]
            r-=r&-r
        return s

n = int(input())
M = 10**5+5
le = M*2

point = [[] for i in range(le)]
for i in range(n):
    x,y = map(int,input().split())
    point[x+y].append(x-y+M)

q = int(input())
ABK = []
for i in range(q):
    a,b,k = map(int,input().split())
    ABK.append([a+b,a-b+M,k])

L = [-1]*q
R = [le]*q

for _ in range(18):
    bit = BIT(le)
    query = [[] for i in range(le)]
    count = [0]*q
    for i,(x,y,_) in enumerate(ABK):
        s = (L[i]+R[i])//2
        query[max(0,x-s)].append(i+q)
        query[min(le-1,x+s+1)].append(i)

    for i in range(le):
        for ind in query[i]:
            sign = 1 if ind < q else -1
            ind = ind%q
            s = (L[ind]+R[ind])//2
            c = ABK[ind][1]
            count[ind] += sign*bit.sum(max(0,c-s),min(le,c+s+1))

        for p in point[i]:
            bit.add(p,1)
    for i in range(q):
        if R[i] == L[i]+1:
            continue
        
        if count[i] >= ABK[i][2]:
            R[i] = (L[i]+R[i])//2
        else:
            L[i] = (L[i]+R[i])//2

for i in R:
    print(i)