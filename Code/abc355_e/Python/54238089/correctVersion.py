def solve(n, l, r):
    """
    2^i*jと2^i*(j+1)に辺を張ったグラフ問題として考える
    BFSで頂点r+1から頂点lへの最短経路を割り出す
    頂点が増える方向なら加算、減る方向なら減算として、最終的な解を求める
    """
    ub = 1<<n

    # 2^i*jと2^i*(j+1)に対して辺を張る
    E = [[] for _ in range(ub+1)]
    for i in range(n+1):
        j = 0
        while (2**i) * (j+1) <= ub:
            x = (2**i) * j
            y = (2**i) * (j+1)
            E[x].append(y) # 足す処理
            E[y].append(x) # 引く処理
            j += 1

    # BFSで頂点r+1から各頂点への最短経路を割り出す
    parent = [None] * (ub+1)
    parent[r+1] = -1
    que = [r+1]
    for v in que:
        for u in E[v]:
            if parent[u] is None:
                parent[u] = v
                que.append(u)

    # 頂点lから頂点r+1への経路を求める
    res = [] # (i, j, -1 | +1)
    x = l
    while parent[x] != -1:
        y = parent[x]
        sign = 1
        if x > y:
            sign = -1
            x, y = y, x
        i = (y - x).bit_length() - 1 # y - x == 2**i
        j = x // (2**i)
        res.append((i, j, sign))
        if sign == 1:
            x = y
    return res

n, l, r = map(int, input().split())

ans = 0
for i, j, sign in solve(n, l, r):
    print(f"? {i} {j}", flush=True)
    t = int(input())
    ans += t * sign
print(f"! {ans % 100}")

