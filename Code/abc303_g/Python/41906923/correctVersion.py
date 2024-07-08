#ABC303G Bags Game

'''
両端から1袋もらう。
A円賄賂してB個もらうか、C円賄賂してD個もらうか。

愚直な区間DPを書くと
DP[i][L][R]: 手番がiで、区間が[L,R)のとき、最適な操作をしたときのなにか
             i=0: 高橋君(先手), X-Yを格納  i=1: 青木君(後手), Y-Xを格納
初期化はDP[i][x][x]=0
遷移はi=0なら max(DP[1][区間]+利得), i=1なら min(DP[0][区間]-利得)
ただ遷移がきついかな。L,Rの選び方でO(N^2)はいいけれど。

左右から取るクエリはO(1)だからこれはそのままでよくて、
区間クエリかな･･･ 累積和取っておけば楽できないかな？

大変そうだけど実装しよう。

しまった、これ連続した区間を根こそぎするわけじゃないのか。
いや、まだなんとかなる。取らない区間は連続しているから･･･
取らない区間の最小値を探す感じで遷移をすれば大丈夫だろう。

くっっそやばい実装になるんですが　本当にこれでいいんですか
前処理 O(N^2 * logN)、本処理O(N^2 * logN)だから正当！という話もあるんでしょうけれど

やっっっっっばいなこれ、方針はわかるけど実装が重すぎる

よく考えたら先手番･後手番でDPをふたつ持つ必要ないな？
DP[L][R]: 区間[L,R)からゲームを開始したときの、プレイヤーの最大利得
とすればDP減らせる。そうしろ。

SegTreeだとTLE スライド最小値を使います
'''
#Segment Tree: O(logN)
class SegmentTree:                                    # Segment Tree
    def __init__(self,n,identity_e,combine_f):        # 適応条件: 単位元eがある、互換可能
        self._n=n; self._size=1                       # モノイド(単位元)の例:
        while self._size<self._n:self._size<<=1       #  足し算 0, かけ算 1, 最小 INF,
        self._identity_e=identity_e                   #  最大 -INF, LCM(最小公倍数) 1
        self._combine_f=combine_f                     #
        self._node=[self._identity_e]*2*self._size    # combine_f には関数を指定する
                                                      # def文で関数を自作してもいいし、
    def build(self,array):                            #  from operator import xor
        assert len(array)==self._n,'array too large'  # のようにimportしてもよい
        for i,v in enumerate(array,start=self._size): #
            self._node[i]=v                           # build: セグ木を建てる
        for i in range(self._size-1,0,-1):            # 異常時はassert関数でエラーを報告
            self._node[i]=self._combine_f(self._node[i<<1|0],self._node[i<<1|1])
                                                      #
    def update(self,index,value):                     # update: 一点更新 O(logN)
        i=self._size+index; self._node[i]=value       # 地点i(0-indexed)を更新する
        while i-1:                                    # 同時に上位のセグメントも更新する
            i>>=1                                     #
            self._node[i]=self._combine_f(self._node[i<<1|0],self._node[i<<1|1])
                                                      #
    def fold(self,L,R):                               # fold: 区間取得 O(logN)
        L+=self._size; R+=self._size                  # 区間 [L,R) の特定値を取得する
        vL,vR=[self._identity_e]*2                    #
        while L<R:                                    # nodeの遷移の考え方
            if L&1:                                   #  ---1---  L: 自身より右の最小
                vL=self._combine_f(vL,self._node[L])  #  -2- -3-  R: 自身-1より左の最小
                L+=1                                  #  4 5 6 7  Rは計算より先に-1の
            if R&1:                                   #           処理をする点に注意
                R-=1                                  # R---1---L
                vR=self._combine_f(self._node[R],vR)  # R-2- LLL. 例: L=6, R=5
            L>>=1; R>>=1                              # .R.5 L 7      Rの移動が変則的
        return self._combine_f(vL,vR)                 #  ←R L→

#スライド最小値: A[i,i+d)の区間幅Kの最小値を与える 左端は超過する点に注意
from collections import deque
def slide_min(A,d):
    N=len(A); L=[0]*N; Q=deque()
    for i in range(N-1,-1,-1):
        while len(Q) and Q[0]>=i+d: Q.popleft()  #番長が卒業
        while len(Q) and A[Q[-1]]>A[i]: Q.pop()  #優秀な後輩
        Q.append(i); L[i]=A[Q[0]]
    return L


f=lambda:list(map(int,input().split()))

#入力受取  sX[i]: X[:i-1]の累積和、X[L:R]の和はsX[R]-sX[L]で計算可能
N,A,B,C,D=f(); X=f(); sX=[0]*(N+1)
for i in range(N): sX[i+1]=sX[i]+X[i]

#DP[L][R]: 手番がiのとき、区間[L,R)に対して最適行動をしたときのX-Y ないし Y-X
DP=[[-10**18]*(N+1) for _ in range(N+1)]
for L in range(N+1): DP[L][L]=0

#幅が小さい順にDPを更新
for d in range(1,N+1):
    qB,qD=min(B,d),min(D,d); restB,restD=d-qB,d-qD

    #nB,nD: DP[L][R](予想される被害額)にX[L,R]を足した値
    nB,nD=[0]*(N-restB+1),[0]*(N-restD+1)
    for i in range(N-restB+1): nB[i]=sX[i+restB]-sX[i]+DP[i][i+restB]
    for i in range(N-restD+1): nD[i]=sX[i+restD]-sX[i]+DP[i][i+restD]
    
    '''
    #SegTree: O(NlogN)で毎回更新 だとTLE
    STB=SegmentTree(N-restB+1,10**18,min); STB.build(nB)
    STD=SegmentTree(N-restD+1,10**18,min); STD.build(nD)
    '''
    #スライド最小値: O(N)でnB,nDの区間最小値を決定
    smB,smD=slide_min(nB,d-restB+1),slide_min(nD,d-restD+1)

    #DP[Lt][Lt+d]を計算
    for Lt in range(N-d+1):
        Rt=Lt+d
        #クエリ1. 左右から1個ずつ取る
        DP[Lt][Rt]=max(DP[Lt][Rt], X[Lt]-DP[Lt+1][Rt], X[Rt-1]-DP[Lt][Rt-1])
        #クエリ2. 左右からqB/qD個取り、連続するrestB/restD個を残す
        DP[Lt][Rt]=max(DP[Lt][Rt], sX[Rt]-sX[Lt]-A-smB[Lt], sX[Rt]-sX[Lt]-C-smD[Lt])
print(DP[0][N])