def solve(h, w, m, t, a, x):
    a = [_-1 for _ in a]
    cnt = {color: 0 for color in x} # 色ごとの塗られたマスの個数
    S_row = set() # すでに塗られた行の集合
    S_col = set() # すでに塗られた列の集合
    # 逆順に塗られたマスの数を調べていく
    for k in range(m-1, -1, -1):
        if t[k] == 1: # a[k]列目のマスを色x[k]で塗る
            if not a[k] in S_row:
                S_row.add(a[k])
                cnt[x[k]] += w - len(S_col)
        else: # a[k]行目のマスを色x[k]で塗る
            if not a[k] in S_col:
                S_col.add(a[k])
                cnt[x[k]] += h - len(S_row)
    cnt[0] = h * w - sum(num for color, num in cnt.items() if color != 0)
    return {color: num for color, num in cnt.items() if num > 0}

h, w, m = map(int, input().split())
t, a, x = zip(*[map(int, input().split()) for i in range(m)])
color_num_mapping = solve(h, w, m, t, a, x)
print(len(color_num_mapping))
for color, num in sorted(color_num_mapping.items()):
    print(color, num)

