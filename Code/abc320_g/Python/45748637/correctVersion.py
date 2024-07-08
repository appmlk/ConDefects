import sys
input = lambda: sys.stdin.readline().rstrip()

from collections import deque,defaultdict as dd
INF=10**18

##二部マッチング
##参考:https://snuke.hatenablog.com/entry/2019/05/07/013609


class Bipartile_Matching:

    def __init__(self,L,R):


        ##L2R:Lから見たRのマッチングを記録
        ##R2L:Rから見たLのマッチングを記録
        ##backpath:L側に逆辺が張られている場合の辿る先
        ##root:逆辺を考慮したLの始点を記録

        self.L=L
        self.R=R
        self.L2R=[-1]*L
        self.R2L=[-1]*R
        self.backpath=[-1]*L
        self.root=[-1]*L
        self.edge=dd(list)

    def add_edge(self,s,t):
        self.edge[s].append(t)


    def match(self):
        res=0
        f=True
        while f:
            f=False
            q=deque()
            for i in range(self.L):
                ##まだマッチング対象が見つかっていなければ
                ##iを始点としてキューに追加
                if self.L2R[i]==-1:
                    self.root[i]=i
                    q.append(i)

            while q:
                s=q.popleft()

                ##逆辺を辿った先がすでに決まっていればcontinue
                if ~self.L2R[self.root[s]]:continue
                
                ##始点から接続されている辺を全探索する
                for t in self.edge[s]:

                    if self.R2L[t]==-1:
                        ##逆辺が存在する場合は辿っていく
                        while t!=-1:
                            self.R2L[t]=s
                            self.L2R[s],t=t,self.L2R[s]
                            s=self.backpath[s]

                        f=True
                        res+=1
                        break

                    ##仮のtに対するマッチング候補の情報を更新しキューに追加する
                    temps=self.R2L[t]
                    if ~self.backpath[temps]:continue

                    self.backpath[temps]=s
                    self.root[temps]=self.root[s]
                    q.append(temps)


            ##更新があれば逆辺・始点情報を初期化する
            if f:
                self.backpath=[-1]*self.L
                self.root=[-1]*self.L

        return res

def judge(mid,j):

    ##ここに判定条件を書く

    # 二分マッチングを作成する
    # 左辺は座標圧縮したリールの停止時間
    # 右辺はリール
    L=len(t2n)
    G=Bipartile_Matching(L,N)

    for i in range(N):
        for k in range(N):
            t=i2t[i][j][k]

            # 時間がmid以下ならパスを繋ぐ
            if t<=mid:

                # 座標圧縮する
                n=t2n[t]

                # 時間とリールでパスを繋ぐ
                G.add_edge(n,i)

    # マッチングを解いて数を数える
    cnt=G.match()
    
    if cnt==N:return True
    else:return False

##ng,okの条件に気をつける
def meguru_bisearch(ng, ok ,j):

    while abs(ok-ng)>1:
        mid=(ok+ng)//2
        if judge(mid,j):ok=mid
        else:ng=mid

    return ok


# 各数字に対して時間とリールで
# マッチングといわれると解けそう
N,M=map(int,input().split())

# i2t[i][j]:リールiの数字jが存在する時間をN個まで記録
i2t=[[[] for _ in range(10)] for _ in range(N)]

# n2t[j]:数字jがすべてのリール中で出現する時間tを記録する
#        後でソートする
n2t=[set() for _ in range(10)]

# 座標圧縮を行う
# 各リールに数字jが出現する場合のインデックスを
# まとめてソートする
for i in range(N):
    S=input()
    for t,j in enumerate(S):
        j=int(j)
        if len(i2t[i][j])<N:
            i2t[i][j].append(t)
            n2t[j].add(t)

    # 各数字に対して出現個数がN個に満たない場合は追加する
    for j in range(10):
        if len(i2t[i][j]):
            k=0

            while len(i2t[i][j])<N:
                t=i2t[i][j][k]+M
                i2t[i][j].append(t)
                n2t[j].add(t)

                k+=1


res=INF

# 数字jで揃えることを考える
for j in range(10):

    # まずすべてのリールに数字jが出現することを確認する
    # しなければcontinue
    f=False
    for i in range(N):
        if len(i2t[i][j])==0:f=True
    if f:continue


    # 座標圧縮する
    # t2n[t]:数字jにおいて時間tが出現する順番nを記録
    t2n=dd(int)

    # n2t[j][n]:数字jのn番目に現れる時間tを記録
    n2t[j]=sorted(n2t[j])
    for n,t in enumerate(n2t[j]):t2n[t]=n

    # リールi,数字jのk番目の出現時間mに対応する
    # 圧縮後のインデックスnを確認していく

    # ここで時間について二分探索しながら
    # 辺の追加を行ってマッチングが成立する
    # 最小の時間を求めるのか
    res=min(res,meguru_bisearch(-1,max(n2t[j]),j))

if res==INF:print(-1)
else:print(res)