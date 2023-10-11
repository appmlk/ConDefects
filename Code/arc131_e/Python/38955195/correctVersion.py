class List2D:

    def __init__(self,H,W,grid):
        self.H,self.W=H,W
        self.H0,self.W0=H,W
        self.grid=grid
        self._reverseH=0
        self._reverseW=0
        self.trans=0

    @classmethod
    def full(cls,H,W,fill_value):
        return cls(H,W,[fill_value]*(H*W))

    @classmethod
    def from_grid(cls,grid):
        tmp=[]
        for array in grid:
            tmp.extend(array)
        return cls(len(grid),len(grid[0]),tmp)

    def __getitem__(self, item):
        if type(item) is tuple:
            h,w=item
            h,w=self.map(h,w)
            return self.grid[h*self.W0+w]
        else:
            sys.stderr.write("getitem: not grid[h][w] but grid[h,w]\n")
            tmp=[]
            for w in range(self.W):
                h,w=item,w
                h,w=self.map(h,w)
                tmp.append(self.grid[h*self.W0+w])
            return tmp

    def __setitem__(self,item,val):
        h,w=item
        h,w=self.map(h,w)
        self.grid[h*self.W0+w]=val

    def reverseH(self):
        if self.trans==0:
            self._reverseH=1-self._reverseH
        else:
            self._reverseW=1-self._reverseW

    def reverseW(self):
        if self.trans==1:
            self._reverseH=1-self._reverseH
        else:
            self._reverseW=1-self._reverseW

    def transpose(self):
        self.H,self.W=self.W,self.H
        self.trans=1-self.trans

    def rotate(self,time=1):
        time%=4
        for _ in range(time):
            self.transpose()
            self.reverseH()

    def map(self,h,w):
        if self.trans:
            h,w=w,h
        if self._reverseH:
            h=self.H0-1-h
        if self._reverseW:
            w=self.W0-1-w
        return h,w

    def accumulate(self):
        res=self.copy()
        for h in range(self.H-1):
            for w in range(self.W):
                res[h+1,w]+=res[h,w]
        for w in range(self.W-1):
            for h in range(self.H):
                res[h,w+1]+=res[h,w]
        return res

    def get_range(self,h0,w0,h1,w1):
        """ sum in [h0,h1)*[w0,w1) after accumulate """
        if h0>=h1 or w0>=w1:
            return 0
        res=self[h1-1,w1-1]
        if h0>=1:
            res-=self[h0-1,w1-1]
        if w0>=1:
            res-=self[h1-1,w0-1]
        if h0>=1 and w0>=1:
            res+=self[h0-1,w0-1]
        return res

    def make_list(self):
        return list(self)

    def __iter__(self):
        for h in range(self.H):
            tmp=[]
            for w in range(self.W):
                tmp.append(self[h,w])
            yield tmp

    def __str__(self):
        res=[]
        for h in range(self.H):
            tmp=[]
            for w in range(self.W):
                tmp.append(self[h,w])
            res.append(str(tmp))
        res.append("")
        return "\n".join(res)

    def __len__(self):
        return self.H

    def __eq__(self, other):
        if type(other) is List2D:
            if self.H==other.H and self.W==other.W:
                for h in range(self.H):
                    for w in range(self.W):
                        if self[h,w]!=other[h,w]:
                            return False
                return True
            else:
                return False
        else:
            if self.H==len(other) and self.W==len(other[0]):
                for h in range(self.H):
                    for w in range(self.W):
                        if self[h,w]!=other[h][w]:
                            return False
                return True
            else:
                return False

    def copy(self):
        res=List2D.full(self.H,self.W,0)
        for h,array in enumerate(self):
            for w,val in enumerate(array):
                res[h,w]=val
        return res



##############################################################
def example():
    global input
    example = iter(
        """
2 3
1 2 3
4 5 6
        """
            .strip().split("\n"))
    input = lambda: next(example)
##############################################################
import sys
input = sys.stdin.readline
from itertools import *
from collections import Counter

def make(N):
    for P in product(range(1,4),repeat=N*(N-1)//2):
        data=List2D.full(N,N,0)
        c=0
        C=Counter(P)
        if not C[1]==C[2]==C[3]:
            continue
        for i in range(N):
            for j in range(i+1,N):
                data[j,i]=data[i,j]=P[c]
                c+=1
        yield data

def check(data):
    N=len(data)
    f=1
    for i in range(N):
        for j in range(i+1,N):
            for k in range(j+1,N):
                S={data[i,j],data[j,k],data[k,i]}
                if len(S)==3:
                    f=0
                    break
            if f==0:
                break
        if f==0:
            break
    return f


def main(N):
    if N*(N-1)%6!=0:
        return -1
    if N<=5:
        return -1

    W=N*(N-1)//6
    cnt=0
    dp=[List2D.full(W+1,W+1,0) for _ in range(N)]
    dp[0][0,0]=1
    for n in range(1,N):
        for a in range(min(cnt+1,W+1)):
            for b in range(min(cnt+1,W+1)):
                dp[n][a,b]|=dp[n-1][a,b]
                if a+n<=W:
                    dp[n][a+n,b]|=dp[n-1][a,b]
                if b+n<=W:
                    dp[n][a,b+n]|=dp[n-1][a,b]
        cnt+=n

    path=[]
    X=[-1]*N
    a,b,=W,W
    for n in range(1,N)[::-1]:
        if a-n>=0 and dp[n-1][a-n,b]==1:
            path.append((0,n))
            X[n]=0
            a-=n
        elif b-n>=0 and dp[n-1][a,b-n]==1:
            path.append((1,n))
            X[n]=1
            b-=n
        elif dp[n-1][a,b]==1:
            path.append((2,n))
            X[n]=2
    return X

N=int(input())
res=main(N)
if res==-1:
    print("No")
else:
    print("Yes")
    d=["R","W","B"]
    for i in range(1,N)[::-1]:
        print(d[res[i]]*i)