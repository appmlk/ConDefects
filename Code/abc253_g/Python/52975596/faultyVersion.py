n, l, r = map(int, input().split())


def calc(x):
    ret = []
    val = 0
    for i in range(n):
        if x < n - 1 - i:
            val = i
            break
        x -= n - 1 - i

    for i in range(val):
        ret.append(n - 1 - i)

    for i in range(val, n):
        ret.append(i - val)

    for i in range(x):
        ret[val], ret[val + i + 1] = ret[val + i + 1], ret[val]

    return ret


x = calc(l - 1)
y = calc(r)


idx_x = [0] * n
idx_y = [0] * n

for i in range(n):
    idx_x[x[i]] = i
    idx_y[y[i]] = i

ans = [0] * n

for i in range(n):
    ans[i] = idx_x[y[i]]

print(*[x + 1 for x in ans])
