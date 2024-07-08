
class SegTree:
    def __init__(self,lis,segfunc,ide_ele):
        self.lis=lis
        self.segfunc=segfunc
        self.ide_ele=ide_ele
        self.n=len(lis)
        nb=bin(self.n)[2:]
        if self.n==1<<len(nb)-1:
            self.leaves=1<<len(nb)-1
        else:
            self.leaves=1<<(len(nb))
        self.array=[self.ide_ele for _ in range(self.leaves*2)]
        for i in range(self.n):
            self.update(i,self.lis[i])
    
    def update(self,ind,x):#加算ではなく更新
        now=ind+self.leaves
        self.array[now]=x
        while now>=2:
            if now%2:
                self.array[now//2]=self.segfunc(self.array[now],self.array[now-1])
            else:
                self.array[now//2]=self.segfunc(self.array[now],self.array[now+1])
            now//=2
    def query(self,left,right): #半開区間
        calc_ind=[]
        stack=[(1,0,self.leaves)]
        while len(stack):
            now,n_l,n_r=stack.pop()
            
            if n_r<left or n_l>=right:
                continue
            elif n_l<left or n_r>right:
                if 2*now+1<len(self.array):
                    stack.append((2*now,n_l,(n_r+n_l)//2))
                    stack.append((2*now+1,(n_r+n_l)//2,n_r))
                continue
            else:
                calc_ind.append(now)
        
        val=self.ide_ele
        for i in calc_ind:
            val=self.segfunc(self.array[i],val)
        return val
    
from math import gcd
N,Q=map(int,input().split())
A=list(map(int,input().split()))
B=list(map(int,input().split()))
C=[]
D=[]
for i in range(N-1):
    C.append(A[i+1]-A[i])
    D.append(B[i+1]-B[i])

segC=SegTree(C,gcd,0)
segD=SegTree(D,gcd,0)

for _ in range(Q):
    h1,h2,w1,w2=map(lambda s: int(s)-1 , input().split())
    Cgcd=segC.query(h1,h2)
    Dgcd=segD.query(w1,w2)
    print(gcd(Cgcd,Dgcd,A[h1]+B[w1]))
    