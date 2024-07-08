n = int(input())
A = list(map(lambda x: int(x)-1, input().split()))
d = 10
seen = [-1] * d
pair = [[-1] * d for _ in range(d)]

ret = 0
for r, rv in enumerate(A):
    cnt = -1
    for mv in range(d):
        lv = 2*mv - rv
        if lv < 0 or lv >= d: continue
        # print(lv, mv, pair[lv][mv])
        cnt = max(cnt, pair[lv][mv])
    ret += cnt + 1
    # print(r, rv, cnt)
    for lv in range(d):
        if seen[lv] == -1: continue
        pair[lv][rv] = max(pair[lv][rv], seen[lv])
    seen[rv] = r

    # print("seen", seen)
    # print("pair")
    # for pi in pair:
    #     print(pi)
print(ret)


