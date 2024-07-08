import sys
input = lambda :sys.stdin.readline()[:-1]
ni = lambda :int(input())
na = lambda :list(map(int,input().split()))
yes = lambda :print("yes");Yes = lambda :print("Yes");YES = lambda : print("YES")
no = lambda :print("no");No = lambda :print("No");NO = lambda : print("NO")
#######################################################################
inf = 10**18
class SegmentTree:
    # 初期化処理
    # f : SegmentTreeにのせるモノイド
    # default : fに対する単位元
    def __init__(self, size, f=lambda x,y : min(x,y), default=inf):
        self.size = 2**(size-1).bit_length() # 簡単のため要素数Nを2冪にする
        self.default = default
        self.dat = [default]*(self.size*2) # 要素を単位元で初期化
        self.f = f
 
    def update(self, i, x):
        i += self.size
        self.dat[i] = x
        while i > 0:
            i >>= 1
            self.dat[i] = self.f(self.dat[i*2], self.dat[i*2+1])
    def updatef(self, i, x):
        i += self.size
        self.dat[i] = self.f(self.dat[i],x)
        while i > 0:
            i >>= 1
            self.dat[i] = self.f(self.dat[i*2], self.dat[i*2+1])
 
    def query(self, l, r):
        l += self.size
        r += self.size
        lres, rres = self.default, self.default
        while l < r:
            if l & 1:
                lres = self.f(lres, self.dat[l])
                l += 1
 
            if r & 1:
                r -= 1
                rres = self.f(self.dat[r], rres) # モノイドでは可換律は保証されていないので演算の方向に注意
            l >>= 1
            r >>= 1
        res = self.f(lres, rres)
        return res
 
    def query2(self):
        s = 1
        #print(self.size)
        while s<self.size:
            #print(s)
            if self.dat[s*2]>self.dat[s*2+1]:
                s = s*2
            else:
                s = s*2+1
        return s-self.size
    
    def query3(self, x):
        s = 1
        if self.dat[1][1] < x:
            return -1
        #print(self.dat)
        while s < self.size:
            if self.dat[s*2][1] >= x:
                s = s * 2
            else:
                x -= self.dat[s*2][0]
                s = s * 2+1
        return s - self.size



n,b,q = na()
a = [i - b for i in na()]

def f(a, b):
    c = a[0]+b[0]
    return c, max(a[1], a[0]+b[1])

e = (0, -inf)
st = SegmentTree(n, f, e)
for i in range(n):
    st.update(i, (a[i], a[i]))
#-2, -5,3,3,2
#-2, 4, 3,3,2
for _ in range(q):
    c, x = na()
    c -= 1
    st.update(c, (x-b, x-b))
    y = st.query3(0)
    """print(y)
    for i in range(n):
        print(st.query(0, i+1))"""
    if y == -1:
        print(st.query(0, n)[0]/n+b)
    else:
        print(st.query(0, y+1)[0]/(y+1)+b)
        
