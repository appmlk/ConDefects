N, M, H, K = map(int, input().split())
S = input()
items = set(tuple(map(int, input().split())) for _ in range(M))
#print(items)
    
# シミュレーションする
res = True
x, y = 0, 0
for c in S:
    # 移動する
    if c == 'R': x += 1
    elif c == 'L': x -= 1
    elif c == 'U': y += 1
    else: y -= 1

    # 体力を 1 減らす (0 未満になったら倒れる)
    H -= 1
    if H < 0: res = False

    # アイテムがあって体力が不足していたら回復する
    if (x, y) in items and H < K:
        H = K
        items.remove((x, y))  # アイテムを削除する

print("Yes" if res else "No")