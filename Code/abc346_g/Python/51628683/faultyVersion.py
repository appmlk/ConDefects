class Lazy_Evaluation_Tree():
    def __init__(self, L, op, unit, act, comp, id):
        """ op を演算, act を作用とするリスト L の Segment Tree を作成

        op: 演算
        unit: Monoid op の単位元 ( xe=ex=x を満たす e )
        act: 作用素
        comp: 作用素の合成
        id: 恒等写像

        [条件] M: Monoid, F={f: F x M→ M: 作用素} に対して, 以下が成立する.
        F は恒等写像 id を含む.つまり, 任意の x in M に対して id(x)=x
        F は写像の合成に閉じている. つまり, 任意の f,g in F に対して, comp(f,g) in F
        任意の f in F, x,y in M に対して, f(xy)=f(x) f(y) である.

        [注記]
        作用素は左から掛ける. 更新も左から.
        """

        self.op=op
        self.unit=unit
        self.act=act
        self.comp=comp
        self.id=id

        N=len(L)
        d=max(1,(N-1).bit_length())
        k=1<<d

        self.data=data=[unit]*k+L+[unit]*(k-len(L))
        self.lazy=[id]*(2*k)
        self.N=k
        self.depth=d

        for i in range(k-1,0,-1):
            data[i]=op(data[i<<1], data[i<<1|1])

    def _eval_at(self, m):
        if self.lazy[m]==self.id:
            return self.data[m]
        return self.act(self.lazy[m],self.data[m])

    #配列の第m要素を下に伝搬
    def _propagate_at(self, m):
        self.data[m]=self._eval_at(m)
        lazy=self.lazy; comp=self.comp

        if m<self.N and self.lazy[m]!=self.id:
            lazy[m<<1]=comp(lazy[m], lazy[m<<1])
            lazy[m<<1|1]=comp(lazy[m], lazy[m<<1|1])
        lazy[m]=self.id

    #配列の第m要素より上を全て伝搬
    def _propagate_above(self, m):
        H=m.bit_length()
        for h in range(H-1, 0, -1):
            self._propagate_at(m>>h)

    #配列の第m要素より上を全て再計算
    def _recalc_above(self, m):
        data=self.data; op=self.op
        eval_at=self._eval_at
        while m>1:
            m>>=1
            data[m]=op(eval_at(m<<1),eval_at(m<<1|1))

    def get(self,k):
        m=k+self.N
        self._propagate_above(m)
        self.data[m]=self._eval_at(m)
        self.lazy[m]=self.id
        return self.data[m]

    #作用
    def action(self, l, r, alpha, left_closed=True, right_closed=True):
        """ 第 l 要素から第 r 要素全てに alpha を作用させる.

        """

        L=l+self.N+(not left_closed)
        R=r+self.N+(right_closed)

        L0=R0=-1
        X,Y=L,R-1
        while X<Y:
            if X&1:
                L0=max(L0,X)
                X+=1

            if Y&1==0:
                R0=max(R0,Y)
                Y-=1

            X>>=1
            Y>>=1

        L0=max(L0,X)
        R0=max(R0,Y)

        self._propagate_above(L0)
        self._propagate_above(R0)

        lazy=self.lazy; comp=self.comp
        while L<R:
            if L&1:
                lazy[L]=comp(alpha, lazy[L])
                L+=1

            if R&1:
                R-=1
                lazy[R]=comp(alpha, lazy[R])

            L>>=1
            R>>=1

        self._recalc_above(L0)
        self._recalc_above(R0)

    def update(self, k, x):
        """ 第 k 要素を x に変更する.
        """

        m=k+self.N
        self._propagate_above(m)
        self.data[m]=x
        self.lazy[m]=self.id
        self._recalc_above(m)

    def product(self, l, r, left_closed=True, right_closed=True):
        """ 第 l 要素から第 r 要素までの総積を求める.

        """

        L=l+self.N+(not left_closed)
        R=r+self.N+(right_closed)

        L0=R0=-1
        X,Y=L,R-1
        while X<Y:
            if X&1:
                L0=max(L0,X)
                X+=1

            if Y&1==0:
                R0=max(R0,Y)
                Y-=1

            X>>=1
            Y>>=1

        L0=max(L0,X)
        R0=max(R0,Y)

        self._propagate_above(L0)
        self._propagate_above(R0)

        vL=vR=self.unit
        op=self.op; eval_at=self._eval_at
        while L<R:
            if L&1:
                vL=op(vL, eval_at(L))
                L+=1

            if R&1:
                R-=1
                vR=op(eval_at(R), vR)

            L>>=1
            R>>=1

        return self.op(vL,vR)

    def all_product(self):
        return self.product(0,self.N-1)

    #リフレッシュ
    def refresh(self):
        lazy=self.lazy; comp=self.comp
        for m in range(1,2*self.N):
            self.data[m]=self._eval_at(m)

            if m<self.N and self.lazy[m]!=self.id:
                lazy[m<<1]=comp(lazy[m], lazy[m<<1])
                lazy[m<<1|1]=comp(lazy[m], lazy[m<<1|1])
            lazy[m]=self.id

    def __getitem__(self,k):
        return self.get(k)

    def __setitem__(self,k,x):
        self.update(k,x)

#==================================================
def solve():
    N = int(input())
    A = list(map(int, input().split()))

    histories = [[0] for _ in range(N + 1)]
    for i, a in enumerate(A, 1):
        histories[a].append(i)

    queries = [[[], []] for _ in range(N + 2)]

    for a in range(N + 1):
        histories[a].append(N + 1)
        for l, c, r in zip(histories[a], histories[a][1:], histories[a][2:]):
            queries[l + 1][0].append((c, r - 1))
            queries[c][1].append((c, r - 1))

    def op(x, y):
        xk, xv = x
        yk, yv = y
        if xk < yk:
            return x
        elif xk > yk:
            return y
        else:
            return (xk, xv + yv)

    def act(a, x):
        return (a + x[0], x[1])

    def comp(a, b):
        return a + b

    S = Lazy_Evaluation_Tree([(0, 1)] * (N + 2), op, (N + 1, 0), act, comp, 0)
    S.update(0, (N + 1, 1))
    S.update(N + 1, (N + 1, 1))

    ans = 0
    for i in range(1, N + 1):
        for l, r in queries[i][0]:
            S.action(l, r, 1)

        k, v = S.product(1, N)
        if k == 0:
            ans += N - v

        for l, r in queries[i][1]:
            S.action(l, r, -1)

    return ans

#==================================================
print(solve())
