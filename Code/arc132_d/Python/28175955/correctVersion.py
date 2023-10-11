n, m = map(int, input().split())

s = input()
t = input()

d = []
j = 0
cj = 0
ci = 0
for i in range(n + m):
    if s[i] == '0':
        ci += 1
    else:
        while t[j] != '1':
            cj += 1
            j += 1
        j += 1
        p = (ci, cj) if ci < cj else (cj, ci)
        d.append(p)
ans = 0
for _ in range(2):
    res = max(0, n - 1)
    frm = 0
    if _ == 1:
        cnt = 0
        while frm < len(d) and d[frm][0] == 0:
            frm += 1
            cnt += 1
        if cnt == 0:
            continue
        res += cnt - 1
    nxt = 10 ** 9
    now = 0
    # print(d, frm, res)
    for (l, r) in d[frm:]:
        # print(l, r, now, res)
        if nxt < l:
            res += now - 1 - 1
            now = 1
            nxt = r
        else:
            nxt = min(nxt, r)
            now += 1
    if now:
        # print('a', r)
        if nxt >= n:
            res += now - 1
        else:
            res += now - 1 - 1
    # print(res)
    ans = max(ans, res)
print(ans)