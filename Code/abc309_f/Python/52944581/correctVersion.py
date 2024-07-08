#N,M=map(int, input().split())
N=int(input())
D={};E=[];F=[]
for i in range(N):
  B=list(map(int, input().split()))
  B=sorted(B)
  a,b,c=B 
  F.append(b)
  if a not in D:
    D[a]=[];E.append(a)
  D[a].append((b,c))
#print(D,E)
E=sorted(E)
FF={}
F=sorted(list(set(F)))
for i in range(len(F)):
  FF[F[i]]=i
#print(FF)

#####segfunc#####
def segfunc(x, y):
  return max(x,y)
#################

#####ide_ele#####
ide_ele =0
#################

class SegTree:
    """
    init(init_val, ide_ele): 配列init_valで初期化 O(N)
    update(k, x): k番目の値をxに更新 O(logN)
    query(l, r): 区間[l, r)をsegfuncしたものを返す O(logN)
    """
    def __init__(self, init_val, segfunc, ide_ele):
        """
        init_val: 配列の初期値
        segfunc: 区間にしたい操作
        ide_ele: 単位元
        n: 要素数
        num: n以上の最小の2のべき乗
        tree: セグメント木(1-index)
        """
        n = len(init_val)
        self.segfunc = segfunc
        self.ide_ele = ide_ele
        self.num = 1 << (n - 1).bit_length()
        self.tree = [ide_ele] * 2 * self.num
        # 配列の値を葉にセット
        for i in range(n):
            self.tree[self.num + i] = init_val[i]
        # 構築していく
        for i in range(self.num - 1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2 * i], self.tree[2 * i + 1])

    def update(self, k, x):
        """
        k番目の値をxに更新
        k: index(0-index)
        x: update value
        """
        k += self.num
        self.tree[k] = x
        while k > 1:
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k ^ 1])
            k >>= 1

    def query(self, l, r):
        """
        [l, r)のsegfuncしたものを得る
        l: index(0-index)
        r: index(0-index)
        """
        res = self.ide_ele

        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r - 1])
            l >>= 1
            r >>= 1
        return res

G=[0]*(N+10)
seg = SegTree(G, segfunc, ide_ele)

#print(seg.query(0, 8))
#seg.update(5, 4)
#print(seg.query(0, 8))
f=0
for e in E[::-1]:
  for x,y in D[e]:
    x=FF[x]
    s=seg.query(x+1,N+5)
    if y<s:
      f=1
  for x,y in D[e]:
    x=FF[x]
    m=seg.query(x,x+1)
    seg.update(x,max(m,y))
    
if f==1:
  print('Yes')
else:
  print('No')