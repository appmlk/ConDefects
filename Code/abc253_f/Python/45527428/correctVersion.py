import collections,sys,math,functools,operator,itertools,bisect,heapq,decimal,string,time,random
#sys.setrecursionlimit(10**9)
#n = int(input())
#alist = list(map(int,input().split()))
#alist = []
#s = input()
n,m,q = map(int,input().split())
#for i in range(n):
#    alist.append(list(map(int,input().split())))
query = []
class cheapSegTree:
    def __init__(self,n,segfunc,e):
        self.segfunc=segfunc
        self.num = 1<<(n-1).bit_length()
        self.lazy = [e]*2*self.num
        self.e = e
    def update(self,l,r,x):
        l+=self.num
        r+=self.num
        while l<r:
            if l&1:
                self.lazy[l]=self.segfunc(self.lazy[l],x)
                l+=1
            if r&1:
                self.lazy[r-1]=self.segfunc(self.lazy[r-1],x)
            l>>=1
            r>>=1

    def get(self,i):
        res=self.e
        i+=self.num
        while i:
            res=self.segfunc(res,self.lazy[i])
            i>>=1
        return res
    
cst = cheapSegTree(m,operator.add,0)
okikae = [[] for i in range(n)]
nibannme = []
a = collections.defaultdict(int)
for i in range(q):
    alist = list(map(int,input().split()))
    query.append(alist)
    if alist[0] == 1:
        l,r,x = alist[1],alist[2],alist[3]
        l-=1
        r-=1
        cst.update(l,r+1,x)
    if alist[0] == 2:
        ii,x = alist[1],alist[2]
        ii-=1
        okikae[ii].append((i,x))
    elif alist[0] == 3:
        ii,jj = alist[1],alist[2]
        
        if okikae[ii-1]:
            nibannme.append((okikae[ii-1][-1][0],i,jj-1))
        else:
            a[i] = 0

nibannme.sort(reverse=True)
cst = cheapSegTree(m,operator.add,0)
for i in range(q):
    alist = query[i][:]
    if alist[0] == 1:
        l,r,x = alist[1],alist[2],alist[3]
        l-=1
        r-=1
        cst.update(l,r+1,x)
    if alist[0] == 2:
        while nibannme and nibannme[-1][0] == i:
            a[nibannme[-1][1]] = alist[2]-cst.get(nibannme[-1][2])
            nibannme.pop()
    if alist[0] == 3:

        print(a[i]+cst.get(alist[2]-1))