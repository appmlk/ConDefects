#ABC298G Strawberry War

'''
高々6行6列なのに、なんでこんなに難しそうなんだ。

区間DPに持ち込みたいところだが、切断方法からして難しすぎるんだよな。
より簡単な区間DPの問題はこうだよね。
 「数直線上にケーキがある。適切に分割し、差を最小化せよ」
これだとしても難しいんだけど。残り切断回数を保持したDPにするかもね。

実際にシミュレートすると場合の数どうなるんだろ。
わからんね。解説ACを目指そう。

典型: 最小値を固定して最大値を最小化せよ
とのことだった。いうほど典型か？

ケーキの苺数候補の全列挙は？
これは簡単で、頂点のひとつを固定すればできる。O(H^2 * W^2)くらい。

DP[a][b][c][d][e]: a<=x<b, c<=y<d を満たす部分長方形を、苺の最小個数がX個になるよう
                   e個に切り分けるときの、最大値の最小値
                   不可能ならinf
'''
f=lambda:list(map(int,input().split()))
H,W,T=f(); S=[f() for i in range(H)]; Rh=range(H+1); Rw=range(W+1)
Ichigo=[[[[0]*(W+1) for i in Rw] for j in Rh] for k in Rh]; Candidate=set()
for h in Rh:
    for w in Rw:
        for L in range(h+1,H+1):
            for D in range(w+1,W+1):
                Ichigo[h][L][w][D]=sum(S[i][j] for i in range(h,L) for j in range(w,D))
                Candidate.add(Ichigo[h][L][w][D])
INF=1e100; ans=INF
for X in Candidate:  #各ピースの最小値をXとしてDP
    DP=[[[[[INF]*(T+1) for i in Rw] for j in Rw] for k in Rh] for L in Rh]    
    for e in range(T+1):
        for a in Rh:
            for b in range(a+1,H+1):
                for c in Rw:
                    for d in range(c+1,W+1):
                        if e==0 and Ichigo[a][b][c][d]>=X:
                            DP[a][b][c][d][e]=Ichigo[a][b][c][d]
                        if e and DP[a][b][c][d][e-1]==INF: continue  #強めに枝刈り
                        if e and (b-a+1)*(d-c+1)<e: continue  #時短
                        for t in range(1,e+1):
                            for x in range(a+1,b):
                                DP[a][b][c][d][e]=min(DP[a][b][c][d][e],
                                                      max(DP[a][x][c][d][t-1],
                                                          DP[x][b][c][d][e-t]))
                            for x in range(c+1,d):
                                DP[a][b][c][d][e]=min(DP[a][b][c][d][e],
                                                      max(DP[a][b][c][x][t-1],
                                                          DP[a][b][x][d][e-t]))
                        if DP[a][b][c][d][e]<X: DP[a][b][c][d][e]=INF
    ans=min(ans,DP[0][H][0][W][T]-X)
print(ans)
