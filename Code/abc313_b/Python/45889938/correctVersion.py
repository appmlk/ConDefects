#最強の人を求めればいいだけなので、誰かに負けたかどうかを調べる
#負けたことがない人が複数人いたら最強は絞れない
N,M=list(map(int,input().split()))
player=[i for i in range(1,N+1)]
for i in range(M):
    a,b=list(map(int,input().split()))
    if b in player:
        player.remove(b)

if len(player)==1:
    print(player[0])
else:
    print(-1)
    