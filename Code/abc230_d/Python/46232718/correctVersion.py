import sys

input = sys.stdin.buffer.readline
sr = lambda: input().rstrip().decode("ascii")  # 文字列の読み込み
ir = lambda: int(input())  # 数字の読み込み
lr = lambda: list(map(int, input().split()))  # 数字の配列の読み込み

N, D = lr()
x = []
inf = 1 << 60
for i in range(N):
    l, r = lr()
    # 壁の左端、左右、壁の番号
    x.append((l, 0, i))
    # 壁の右端、左右、壁の番号
    x.append((r, 1, i))
sx = sorted(x)
l = []
r = 1 << 60
w = set()
cnt = 0
for p in sx:
    v, f, wn = p[0], p[1], p[2]
    # 壁の右端
    if f:
        # 既に壊されている壁はスキップ
        if wn not in w:
            r = min(r, v)
    # 壁の左端
    else:
        # まだ壊されていない壁がある時
        if r != inf:
            # 最も右端が左側の壁と同時に壊せる範囲よりも左端が右の場合
            if r + D - 1 < v:
                # 今まで見た壁を壊す
                while len(l):
                    w.add(l.pop())
                # パンチをカウント
                cnt += 1
                # 右端をリセット
                r = inf
        # 壊されていない壁リストに追加
        l.append(wn)
# 残った壁を壊す
cnt += 1
print(cnt)
