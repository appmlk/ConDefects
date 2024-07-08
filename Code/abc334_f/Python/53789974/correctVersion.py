import sys

sys.set_int_max_str_digits(0)
#####segfunc#####
def segfunc(x, y):
    return min(x,y)
#################
inf=5*10**15
#####ide_ele#####
ide_ele = inf
#################

class LazySegmentTree:
    """
    init(init_val, ide_ele): 配列init_valで初期化 O(N)
    add(l, r, x): 区間[l, r)にxを加算 O(logN)
    query(l, r): 区間[l, r)をsegfuncしたものを返す O(logN)
    """
    def __init__(self, init_val, segfunc, ide_ele):
        """
        init_val: 配列の初期値
        segfunc: 区間にしたい操作
        ide_ele: 単位元
        num: n以上の最小の2のべき乗
        data: 値配列(1-index)
        lazy: 遅延配列(1-index)
        """
        n = len(init_val)
        self.segfunc = segfunc
        self.ide_ele = ide_ele
        self.num = 1 << (n - 1).bit_length()
        self.data = [ide_ele] * 2 * self.num
        self.lazy = [0] * 2 * self.num
        # 配列の値を葉にセット
        for i in range(n):
            self.data[self.num + i] = init_val[i]
        # 構築していく
        for i in range(self.num - 1, 0, -1):
            self.data[i] = self.segfunc(self.data[2 * i], self.data[2 * i + 1])

    def gindex(self, l, r):
            """
            伝搬する対象の区間を求める
            lm: 伝搬する必要のある最大の左閉区間
            rm: 伝搬する必要のある最大の右開区間
            """
            l += self.num
            r += self.num
            lm = l >> (l & -l).bit_length()
            rm = r >> (r & -r).bit_length()

            while r > l:
                if l <= lm:
                    yield l
                if r <= rm:
                    yield r
                r >>= 1
                l >>= 1
            while l:
                yield l
                l >>= 1

    def propagates(self, *ids):
        """
        遅延伝搬処理
        ids: 伝搬する対象の区間
        """
        for i in reversed(ids):
            v = self.lazy[i]
            if not v:
                continue
            self.lazy[2 * i] += v
            self.lazy[2 * i + 1] += v
            self.data[2 * i] += v
            self.data[2 * i + 1] += v
            self.lazy[i] = 0

    def add(self, l, r, x):
        """
        区間[l, r)の値にxを加算
        l, r: index(0-index)
        x: additional value
        """
        *ids, = self.gindex(l, r)
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                self.lazy[l] += x
                self.data[l] += x
                l += 1
            if r & 1:
                self.lazy[r - 1] += x
                self.data[r - 1] += x
            r >>= 1
            l >>= 1
        for i in ids:
            self.data[i] = self.segfunc(self.data[2 * i], self.data[2 * i + 1]) + self.lazy[i]


    def query(self, l, r):
        """
        [l, r)のsegfuncしたものを得る
        l: index(0-index)
        r: index(0-index)
        """
        *ids, = self.gindex(l, r)
        self.propagates(*ids)

        res = self.ide_ele

        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.data[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.data[r - 1])
            l >>= 1
            r >>= 1
        return res
def dist(x1,y1,x2,y2):
  pw=pow(x1-x2,2)+pow(y1-y2,2)
  return pw**(1/2)

def main():
  N,K=map(int,input().split())
  Sx,Sy=map(int,input().split())
  dists=[inf]*N
  xy=[]
  for _ in range(N):
    x,y=map(int,input().split())
    xy.append((x,y))
  xf,yf=xy[0]
  dists[0]=dist(Sx,Sy,xf,yf)
  #print(dists)
  lseg=LazySegmentTree(dists,segfunc,ide_ele)
  for i in range(N-1):
    xb,yb=xy[i]
    xn,yn=xy[i+1]
    bipass=dist(xb,yb,Sx,Sy)+dist(Sx,Sy,xn,yn)
    direct=dist(xb,yb,xn,yn)
    nd=lseg.query(0,i+1)+bipass
    cur=lseg.query(i+1,i+2)
    ad=round(-(cur-nd),8)
    #print(cur,nd,ad)
    lseg.add(i+1,i+2,ad)
    #dists[i+1]=min(dists[:i+1])+bipass
    if i-K+1>=0:
      lseg.add(i-K+1,i-K+2,10**18)
      #dists[i-K+1]=inf
    lseg.add(0,i+1,direct)
    #for j in range(i+1):
    #  dists[j]+=direct
    #print(bipass,direct,dists)
    #print([lseg.query(j,j+1) for j in range(N)])
  xe,ye=xy[-1]
  res=dist(xe,ye,Sx,Sy)
  print(lseg.query(0,N)+res)
main()