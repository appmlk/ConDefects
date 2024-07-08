def Ternary_Search_Maximize_Integer(L, R, f, arg=False):
    """ 三分探索によって, 整数を定義域とする関数 f の [L,R] における最大値を求める.

    f: [L,R] 内で上に凸または単調増加
    """
    while (R-L)>3:
        a=(2*L+R)//3
        b=(L+2*R)//3

        p=f(a); q=f(b)
        if p>=q:
            R=b
        else:
            L=a

    a=(2*L+R)//3
    b=(L+2*R)//3

    if arg:
        y,argx=f(L),L
        for x in [a,b,R]:
            p=f(x)
            if y<p:
                y,argx=p,x
        return y,argx
    else:
        return max(f(L),f(a),f(b),f(R))

#素数判定
def Is_Prime(N):
    N=abs(N)
    if N<=1: return False

    if (N==2) or (N==3) or (N==5): return True

    r=N%6
    if not(r==1 or r==5): return False

    k=5
    Flag=0
    while k*k<=N:
        if N%k==0: return False

        k+=2+2*Flag
        Flag^=1
    return True
#==================================================
from collections import deque
class MaxFlow:
    inf = float("inf")

    class Arc:
        def __init__(self, source, target, cap, base, direction, id):
            self.source=source
            self.target=target
            self.cap = cap
            self.base = base
            self.rev = None
            self.direction=direction
            self.id=id

        def __repr__(self):
            if self.direction==1:
                return "id: {}, {} -> {}, {} / {}".format(self.id, self.source, self.target, self.cap, self.base)
            else:
                return "id: {}, {} <- {}, {} / {}".format(self.id, self.target, self.source, self.cap, self.base)

    def __init__(self, N=0):
        """ N 頂点のフロー場を生成する.
        """

        self.arc = [[] for _ in range(N)]
        self.__arc_list=[]

    def add_vertex(self):
        self.arc.append([])
        return self.vertex_count()-1

    def add_vertices(self, k):
        n=self.vertex_count()
        self.arc.extend([[] for _ in range(k)])
        return list(range(n,n+k))

    def add_arc(self, v, w, cap):
        """ 容量 cap の有向辺 v → w を加える.
        """

        m=len(self.__arc_list)
        a=self.Arc(v,w,cap,cap,1,m)
        b=self.Arc(w,v,0,cap,-1,m)
        a.rev=b; b.rev=a
        self.arc[v].append(a)
        self.arc[w].append(b)
        self.__arc_list.append(a)
        return m

    def get_arc(self, i, mode=0):
        """ i 番目の辺の情報を得る.

        """

        assert 0<=i<len(self.__arc_list)
        a=self.__arc_list[i]
        if mode:
            return a,a.rev
        else:
            return a

    def get_all_arcs(self):
        return [self.get_arc(i) for i in range(len(self.__arc_list))]

    def vertex_count(self):
        return len(self.arc)

    def arc_count(self):
        return len(self.__arc_list)

    def change_arc(self, i, new_cap, new_flow):
        """ i 番目の辺の情報を変更する.

        """

        assert 0<=i<len(self.__arc_list)
        assert 0<=new_flow<=new_cap

        a=self.__arc_list[i]
        a.base=new_cap; a.cap=new_cap-new_flow
        a.rev.base=new_cap; a.rev.cap=new_flow

    def add_edge(self, v, w, cap):
        """ 容量 cap の無向辺 v → w を加える."""
        self.add_arc(v,w,cap)
        self.add_arc(w,v,cap)

    def __bfs(self, s, t):
        level=self.level=[-1]*self.vertex_count()
        Q=deque([s])
        level[s]=0
        while Q:
            v=Q.popleft()
            next_level=level[v]+1
            for a in self.arc[v]:
                if a.cap and level[a.target]==-1:
                    level[a.target]=next_level
                    if a.target==t:
                        return True
                    Q.append(a.target)
        return False

    def __dfs(self, s, t, up):
        arc = self.arc
        it = self.it
        level = self.level

        st = deque([t])
        while st:
            v = st[-1]
            if v == s:
                st.pop()
                flow = up
                for w in st:
                    a = arc[w][it[w]].rev
                    flow = min(flow, a.cap)
                for w in st:
                    a = arc[w][it[w]]
                    a.cap += flow
                    a.rev.cap -= flow
                return flow
            lv = level[v]-1
            while it[v] < len(arc[v]):
                a = arc[v][it[v]]
                ra = a.rev
                if ra.cap == 0 or lv != level[a.target]:
                    it[v] += 1
                    continue
                st.append(a.target)
                break
            if it[v] == len(arc[v]):
                st.pop()
                level[v]=-1
        return 0

    def max_flow(self, source, target, flow_limit=inf):
        """ source から target に高々 flow_limit の水流を流すとき, "新たに流れる" 水流の大きさ"""

        flow = 0
        while flow < flow_limit and self.__bfs(source, target):
            self.it = [0]*self.vertex_count()
            while flow < flow_limit:
                f = self.__dfs(source, target, flow_limit-flow)
                if f == 0:
                    break
                flow += f
        return flow

    def get_flow(self, mode=0):
        if mode==0:
            return [a.base-a.cap for a in self.__arc_list]
        else:
            F=[[] for _ in range(self.vertex_count())]
            for i,a in enumerate(self.__arc_list):
                F[a.source].append((i, a.target, a.base-a.cap))
            return F

    def min_cut(self,s):
        """ s を 0 に含める最小カットを求める.
        """

        group = [1]*self.vertex_count()
        Q = deque([s])
        while Q:
            v = Q.pop()
            group[v] = 0
            for a in self.arc[v]:
                if a.cap and group[a.target]:
                    Q.append(a.target)
        return group

    def refresh(self):
        for a in self.__arc_list:
            a.cap=a.base
            a.rev.cap=0

#==================================================
def calc(k):
    F=MaxFlow()

    source=F.add_vertex()
    I=F.add_vertices(N)
    target=F.add_vertex()

    for i in range(N):
        if A[i]==1:
            B[i]-=2*k

        if A[i]%2==1:
            F.add_arc(source, I[i], B[i])
        else:
            F.add_arc(I[i], target, B[i])

    inf=float("inf")
    for i in range(N):
        for j in range(N):
            if (A[i]%2==1) and (A[j]%2==0) and P[i][j]:
                F.add_arc(I[i], I[j], inf)

    return F.max_flow(source, target)+k

#==================================================
N=int(input())

A=[0]*N; B=[0]*N
for i in range(N):
    A[i],B[i]=map(int,input().split())

if 1 in A:
    count_1=B[A.index(1)]
else:
    count_1=0

P=[[Is_Prime(A[i]+A[j]) for j in range(N)] for i in range(N)]

print(Ternary_Search_Maximize_Integer(0,count_1//2, calc))
