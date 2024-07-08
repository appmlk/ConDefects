import sys
input = sys.stdin.readline
class BIT:
    def __init__(self,N):
        self.s = (N-1).bit_length()
        self.N = 1<<self.s
        self.data = [0]*(self.N+1)
    def build(self,A):
        for i in range(1,self.N+1):
            self.data[i] += A[i-1]
            if i+(i&-i) <= self.N:
                self.data[i+(i&-i)] += self.data[i]
    def add(self,i,x):
        i += 1
        while i <= self.N:
            self.data[i] += x
            i += i&-i
    def fold(self,l,r):
        res = 0
        while r > 0:
            res += self.data[r]
            r -= r&-r
        while l > 0:
            res -= self.data[l]
            l -= l&-l
        return res
    def all_prod(self):
        return self.data[self.N]
    def max_right_(self,x):
        i = self.N
        if self.data[i] <= x:
            return self.N
        tmp = 0
        for j in range(self.s-1,-1,-1):
            if tmp+self.data[i] <= x:
                tmp += self.data[i]
                i += 1<<j
            else:
                i -= 1<<j
        if tmp+self.data[i] <= x:
            return i
        else:
            return i-1
    def max_right(self,l,x):
        return self.max_right_(self.fold(0,l)+x)

N = int(input())
X_ = set()
data = [[0,0] for _ in range(N)]
for i in range(N):
    a,b = map(int,input().split())
    data[i][0],data[i][1] = a,b
    X_.add(a)
Q = int(input())
query = [list(map(int,input().split())) for _ in range(Q)]
for q in range(Q):
    qu = query[q]
    if qu[0] == 1:
        x,y = qu[1:]
        X_.add(y)
M = len(X_)
X = sorted(X_)
X.reverse()
co = {x : i for i,x in enumerate(X)}
bit0 = BIT(M)
bit1 = BIT(M)
for i in range(N):
    a,b = data[i][0],data[i][1]
    bit0.add(co[a],a*b)
    bit1.add(co[a],b)
for i in range(Q):
    qu = query[i]
    if qu[0] == 1:
        x,y = qu[1:]
        a,b = data[x-1]
        bit0.add(co[a],-a*b)
        bit1.add(co[a],-b)
        bit0.add(co[y],y*b)
        bit1.add(co[y],b)
        data[x-1][0] = y
    if qu[0] == 2:
        x,y = qu[1:]
        a,b = data[x-1]
        bit0.add(co[a],a*y-a*b)
        bit1.add(co[a],y-b)
        data[x-1][1] = y
    if qu[0] == 100:
        x = qu[1]
        if bit1.all_prod() < x:
            print(-1)
            continue
        r = bit1.max_right_(x)
        res = bit0.fold(0,r)
        if r < M:
            res += X[r]*(x-bit1.fold(0,r))
        print(res)