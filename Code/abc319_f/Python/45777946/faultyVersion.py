ir = lambda: int(input()) # 数字の読み込み
lr = lambda: list(map(int, input().split())) # 数字の配列の読み込み

import heapq

N = ir()
P = [[] for _ in range(N)]
S = [0]
G = [0]
potionidx = []

for i in range(1, N):
    p, t, s, g = lr()
    # 木は親から子の方向だけ持つ
    P[p-1].append(i)
    S.append(s)
    G.append(g)
    if t == 2:
        # 敵の強さを持つ必要がないので負数で薬であることを表現
        # どの薬を使ったかをbitで表現するためindexを保存
        S[-1] = ~len(potionidx)
        potionidx.append(i)

def go(q, potionbit, power):
    # 到達可能な頂点が存在する限り回る
    while len(q) > 0 and q[0][0] <= power:
        _, vertex = heapq.heappop(q)
        power += G[vertex]
        # 敵の強さは10**9が上限
        power = min(power, 10**9)
        for v in P[vertex]:
            if S[v] > 0:
                heapq.heappush(q, (S[v], v))
            else:
                # 薬がある場所に到達したことをbitで表現
                potionbit |= 1 << ~S[v]
    return (q, potionbit, power)

potionnum = len(potionidx)
# 薬の数だけbit全探索
dp = [(0, 0, 0)] * (1<<potionnum)
# 薬を使わずに回った状態が初期値
# [(strength, vertex)], potionbit, power
dp[0] = go([(0, 0)], 0, 1)

for i in range(1<<potionnum):
    for k in range(potionnum):
        # k番目の薬をまだ使っていない且つその薬に到達可能
        if not i & (1<<k) and dp[i][1] & (1<<k):
            q, potionbit, power = dp[i]
            potion = G[potionidx[k]]
            power *= potion
            power = min(power, 10**9)
            # k番目の薬を強さ0の敵として最初に取得するようにして帳尻を合わせる
            power -= potion
            # k番目の薬がある頂点から移動し始める
            heapq.heappush(q, (0, potionidx[k]))
            state = go(q, potionbit, power)
            # k番目の薬を使った後のpowerが大きければ更新
            if state[2] > dp[i|(1<<k)][2]:
                dp[i|(1<<k)] = state

# 敵を倒して上昇する強さは1以上なので最終的な強さは最大の敵の強さより1以上大きくなる
print("Yes" if dp[-1][2] > max(S) else "No")
