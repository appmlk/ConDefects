# Accepted. From the official editorial.
K = int(input())
Sx, Sy = [int(x) for x in input().split()]
Tx, Ty = [int(x) for x in input().split()]
# Kずつ右上にずらしておく。
Sx += K
Sy += K
Tx += K
Ty += K
# 上界を入れておく。
dist = max(Tx, Sx) - min(Tx, Sx) + max(Ty, Sy) - min(Ty, Sy)
# 1 < Kのとき、大タイルを経由する移動を考える。
if 1 < K:
    large_start = []  # はじめに到達する大タイルの候補。
    if ((Sx // K) ^ (Sy // K)) & 1:  # 開始位置が大タイルなら。
        large_start.append((Sx // K, Sy // K, 0))  # その大タイル。
    else:  # そうでなければ、四方向いずれかで最も近い大タイルが候補。
        large_start.append((Sx // K - 1, Sy // K, 1 + Sx % K))  # 左。
        large_start.append((Sx // K + 1, Sy // K, K - Sx % K))  # 右。
        large_start.append((Sx // K, Sy // K - 1, 1 + Sy % K))  # 下。
        large_start.append((Sx // K, Sy // K + 1, K - Sy % K))  # 上。
    large_goal = []  # 最後に到達する大タイルの候補。
    if ((Tx // K) ^ (Ty // K)) & 1:  # 終了位置が大タイルなら。
        large_goal.append((Tx // K, Ty // K, 0))  # その大タイル。
    else:  # そうでなければ、四方向いずれかで最も近い大タイルが候補。
        large_goal.append((Tx // K - 1, Ty // K, 1 + Tx % K))  # 左。
        large_goal.append((Tx // K + 1, Ty // K, K - Tx % K))  # 右。
        large_goal.append((Tx // K, Ty // K - 1, 1 + Ty % K))  # 下。
        large_goal.append((Tx // K, Ty // K + 1, K - Ty % K))  # 上。
    # K = 2かどうかで場合分け。
    if K == 2:
        for x, y, d1 in large_start:
            for z, w, d2 in large_goal:
                x_diff = max(x, z) - min(x, z)
                y_diff = max(y, w) - min(y, w)
                dist = min(dist, d1 + d2 + x_diff + y_diff + (max(x_diff, y_diff) - min(x_diff, y_diff)) // 2)
    else:
        for x, y, d1 in large_start:
            for z, w, d2 in large_goal:
                dist = min(dist, d1 + d2 + max(x + y, z + w) - min(x + y, z + w) + max(x + w, z + y) - min(x + w, z + y))
print(dist)