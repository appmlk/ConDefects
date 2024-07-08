import sys
input = sys.stdin.readline

H,W=map(int,input().split())
MAP=[input().strip() for i in range(H)]

mod=998244353

E=[[] for i in range(H*W)]

for i in range(H):
    for j in range(W):
        if i+1<H and MAP[i][j]=="#" and MAP[i+1][j]=="#":
            E[i*W+j].append((i+1)*W+j)
            E[(i+1)*W+j].append(i*W+j)

        if j+1<W and MAP[i][j]=="#" and MAP[i][j+1]=="#":
            E[i*W+j].append(i*W+j+1)
            E[i*W+j+1].append(i*W+j)

SUM=0
ANS=0

LANS=0

V=H*W
USED=[0]*(H*W)
USE = [0]*V
DFS_ORD = [0]*V  # DFSした順に番号をつける。DFS_ORD[i]=xで、頂点iの次数はx
DFS_Parent = [-1]*V  # DFS木の親
LOWLINK = [0]*V  # LOWLINK。後退辺を一回まで使ってたどりつける次数（DFSで何番目にたどりついたか）の最小値。
DFS_Child=[[] for i in range(V)]
for i in range(H*W):

    if USE[i]==0 and MAP[i//W][i%W]=="#":
        ANS+=1
        ROOT = i # ROOTを定める。
        Q = [(ROOT, ROOT)]
        ordnum = 1

        LIST=[]
        DFS_SORT = []  # DFSで見た頂点の順番。

        while Q:
            fr, x = Q.pop()
            if USE[x] == 1:
                continue
            LIST.append(x)
            SUM+=1
            DFS_SORT.append(x)
            if fr != x:
                DFS_Parent[x] = fr
                DFS_Child[fr].append(x)
                DFS_ORD[x] = ordnum
                ordnum += 1
                LOWLINK[x] = DFS_ORD[x]  # LOWLINKをDFS_ORDで初期化。

            USE[x] = 1

            for to in E[x]:
                if USE[to] == 0:
                    Q.append((x, to))


        for i in DFS_SORT[::-1]:  # DFS_ORDの大きい頂点から順番に見て、LOWLINKを更新していく。
            for to in E[i]:
                if to != DFS_Parent[i]:
                    LOWLINK[i] = min(LOWLINK[i], DFS_ORD[to], LOWLINK[to])

        Articulation = []
        ROOT_DEG = 0
        for i in LIST:  # DFS木の各辺を調べる。
            if i == ROOT:
                continue
            if DFS_Parent[i] == ROOT:
                ROOT_DEG += 1
            # DFS木の頂点u（ROOTでない）が関節点になるのは、子から自分の祖先への後退辺がないとき
            if DFS_ORD[DFS_Parent[i]] <= LOWLINK[i] and DFS_Parent[i] != ROOT:
                Articulation.append(DFS_Parent[i])

        for x in LIST:
            if x==ROOT:
                now=-1
                for c in DFS_Child[x]:
                    if DFS_ORD[x]<=LOWLINK[c]:
                        now+=1

            else:
                now=0
                for c in DFS_Child[x]:
                    if DFS_ORD[x]<=LOWLINK[c]:
                        now+=1

            LANS+=now

print((LANS+ANS*SUM)*pow(SUM,mod-2,mod)%mod)


