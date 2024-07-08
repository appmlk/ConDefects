#ABC347F

#二次元累積和  Grid[Sh:Gh)[Sw:Gw)の矩形和を求める(半開区間なので注意)
class Csum2D:
    def __init__(self,H=1,W=1):
        self._H=H; self._W=W; self._G=[[0]*W for _ in range(H)]; self._made=0
    def add(self,h,w,x):
        if -H<=h<H and -W<=w<W and not self._made: self._G[h][w]+=x; return 1
        else: return 0
    def build(self,Grid=0):  #二次元累積和を構築する。グリッドがあれば転記し構築
        if Grid:
            self._H,self._W=len(Grid),len(max(Grid[i] for i in range(len(Grid))))
            self._G=[[0]*self._W for _ in range(self._H)]
            for h in range(self._H):
                for w in range(len(Grid[h])): self._G[h][w]=Grid[h][w]
        for h in range(self._H): self._G[h].append(0)
        self._G.append([0]*(self._W+1)); self._H+=1; self._W+=1; self._made=1
        for h in range(self._H-1):
            for w in range(self._W-1):
                self._G[h][w]+=self._G[h][w-1]+self._G[h-1][w]-self._G[h-1][w-1]
    def query(self,Sh,Sw,Gh,Gw):
        Sh,Sw,Gh,Gw=Sh-1,Sw-1,Gh-1,Gw-1
        return self._G[Gh][Gw]-self._G[Sh][Gw]-self._G[Gh][Sw]+self._G[Sh][Sw]


#Disjoint Sparse Table  構築 O(NlogN), クエリ O(1)
class Disjoint_Sparse_Table:  #結合律(A+B)+C = A+(B+C)が成り立てばOK
    def __init__(self, A, identity_e, function):
        self._N = N = len(A); self._logN = logN = (N - 1).bit_length()
        self._M = M = 2 ** logN; self._e = e = identity_e; self._f = f = function
        self._A = A = A + [e] * (M - N); self._T = T = [[e] * logN for _ in range(M)]
        for x in range(logN):
            t = 1 << x
            for s in range(t, M, t << 1):
                T[s][x] = A[s]
                for j in range(s + 1, s + t, +1): T[j][x] = f(T[j-1][x], A[j])
            for s in range(M - t - 1, -1, -t << 1):
                T[s][x] = A[s]
                for j in range(s - 1, s - t, -1): T[j][x] = f(A[j], T[j+1][x])
    def fold(self, Lt, Rt):  #半開区間[Lt:Rt)の作用値を返す
        Lt, Rt = max(0, Lt), min(self._M, Rt) - 1; x = (Lt ^ Rt).bit_length() - 1
        if not 0 <= Lt <= Rt < self._M: return self._e
        if Lt == Rt: return self._A[Lt]
        return self._f( self._T[Lt][x], self._T[Rt][x] )


#Segment Tree: O(logN)
class SegmentTree:
    def __init__(self, n, identity_e, combine_f): self._n = n; self._size = 1 << (n-1).bit_length(); self._identity_e = identity_e; self._combine_f = combine_f; self._node = [self._identity_e] * 2 * self._size
    def build(self, array):
        assert len(array) == self._n, 'array too large'
        for i,v in enumerate(array, start = self._size): self._node[i] = v
        for i in range(self._size - 1, 0, -1): self._node[i] = self._combine_f(self._node[i<<1|0], self._node[i<<1|1])
    def update(self, index, value):  #一点更新
        i = self._size + index; self._node[i] = value
        while i - 1: i >>= 1; self._node[i] = self._combine_f(self._node[i<<1|0], self._node[i<<1|1])
    def fold(self, L, R):  #区間取得: [L,R)の区間値を得る
        L += self._size; R += self._size; vL = vR = self._identity_e
        while L < R:
            if L & 1: vL = self._combine_f(vL, self._node[L]); L += 1
            if R & 1: R -= 1; vR = self._combine_f(self._node[R], vR)
            L >>= 1; R >>= 1
        return self._combine_f(vL, vR)
    #down: Falseなら単調増加、Trueなら単調減少を仮定する。
    #[Lt:Rt]の作用値がX以上/以下 となる、最小のRtを返す。閉区間なので扱い注意。
    def bisect(self, Lt, X, down = False):
        if Lt >= self._n: return self._n
        now = Lt + self._size; cnt = self._identity_e
        while 1:  #nodeの上昇
            f = now & 3; now = now >> 2 if f == 0 else now >> 1 if f == 2 else now; t = self._combine_f(cnt, self._node[now])
            if not down and t >= X: break
            elif   down and t <= X: break
            else:
                cnt = t; now += 1
                if now & (now - 1) == 0: return self._n
        while now < self._size:  #下降
            Lt, Rt = self._node[now<<1|0], self._node[now<<1|1]
            if not down and self._combine_f(cnt, Lt) >= X: now = now<<1|0
            elif   down and self._combine_f(cnt, Lt) <= X: now = now<<1|0
            else: cnt = self._combine_f(cnt, Lt); now = now<<1|1
        return now - self._size



N, M = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(N)]

#90度回転
def rotate(S):
    return [[S[- h - 1][w] for h in range(N)] for w in range(N)]

#品に割るか、目に割るかを試す
def solve(S):
    C = Csum2D(N, N)
    C.build(S)
    ans = 0

    #1. 目割り
    dp = [- 10 ** 18] * N
    for h in range(N - M + 1):
        for w in range(N - M + 1):
            dp[h] = max(dp[h], C.query(h, w, h + M, w + M))
    DST = Disjoint_Sparse_Table(dp, - 10 ** 18, max)
    for a in range(N):
        for b in range(a + M, N):
            c = b + M
            if c + M <= N:
                ans = max(ans, dp[a] + dp[b] + DST.fold(c, N))

    #2. 品割り
    ST = SegmentTree(N, - 10 ** 18, max)
    for h in range(N - M, -1, -1):
        for w in range(N - M + 1):
            if ST.fold(w, w + 1) < C.query(h, w, h + M, w + M):
                ST.update(w, C.query(h, w, h + M, w + M))
        t = h - M
        if t >= 0:
            for w in range(N - M + 1):
                if w - M >= 0:
                    ans = max(ans, DST.fold(0, t + 1) + ST.fold(0, w - M) + ST.fold(w, N))
    return ans

ans = 0
for t in range(4):
    ans = max(ans, solve(A))
    A = rotate(A)
print(ans)
