from bisect import *
import sys
input=sys.stdin.readline
#####segfunc#####
def segfunc(x, y):
    return x+y					#区間クエリでのfuncを設定
#################
 
#####ide_ele#####
ide_ele =0					#クエリの単位元を設定
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

def main():
  def mgr_bisect(ng,ok):				#ここの部分(mgr_bisect)は改変しない
    while (abs(ok-ng)>1):
      mid=(ok+ng)//2
      if is_ok(mid):
        ok=mid
      else:
        ng=mid
    return ok
  N,M,H=map(int,input().split())
  attacks=[]
  compnum=set()
  compnum.add(0)
  damages=[0]*M
  attackinsts=[]
  for i in range(N):
    a,b=map(int,input().split())
    b-=1
    damages[b]+=a
    compnum.add(damages[b])
    attackinsts.append((a,b))
  compnum=list(compnum)
  compnum.sort()
  #print(compnum)
  num_to_ind={}
  ind_to_num={}
  for i,v in enumerate(compnum):
    num_to_ind[v]=i
    ind_to_num[i]=v
  n=len(compnum)
  cnts=[0]*n
  cnts[0]=M
  sums=[0]*n
  cursuml=[0]*M
  cntseg=SegTree(cnts,segfunc,ide_ele)
  sumseg=SegTree(sums,segfunc,ide_ele)
  allsum=0
  needamulets=[0]*N
  for i in range(N):
    a,b=attackinsts[i]
    cursum=cursuml[b]
    allsum-=cursum
    nxtsum=cursum+a
    allsum+=nxtsum
    cursuml[b]+=a
    curind=num_to_ind[cursum]
    nxtind=num_to_ind[nxtsum]
    #print(curind,nxtind)
    curcnt=cntseg.query(curind,curind+1)
    nxtcnt=cntseg.query(nxtind,nxtind+1)
    #print(curcnt,nxtcnt)
    cntseg.update(curind,curcnt-1)
    cntseg.update(nxtind,nxtcnt+1)
    
    cursums=sumseg.query(curind,curind+1)
    nxtsums=sumseg.query(nxtind,nxtind+1)
    sumseg.update(curind,cursums-cursum)
    sumseg.update(nxtind,nxtsums+nxtsum)
    if allsum<H:continue
    if allsum==H:
      needamulets[i]=1
      continue
    #生き残るため、除く必要がある最小ダメージ
    dif=allsum-H
    def is_ok(arg):
      return sumseg.query(arg,n)>dif
    ind=mgr_bisect(n,-1)
    thre_num=ind_to_num[ind]
    cusum=sumseg.query(ind,n)
    #print([cntseg.query(i,i+1) for i in range(n)])
    #print([sumseg.query(i,i+1) for i in range(n)])
    needamulets[i]=cntseg.query(ind,n)-((cusum-dif)//thre_num)
    
  #print(needamulets)
  ans=[-1]*(M+1)
  for i in range(M+1):
    ans[i]=bisect(needamulets,i)
  print(*ans)
main()