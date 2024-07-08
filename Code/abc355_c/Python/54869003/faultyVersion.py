n, t = map(int, input().split())
a = list(map(int, input().split()))

r = {}
c = {}
d = {1: 0, 2: 0}

ans = -1
for l in range(t):
    ai = a[l]
    i = ai // n
    j = ai % n
    if j != 0:
        if i+1 not in r:
            r[i+1] = 1
        else:
            r[i+1] += 1
        if r[i+1] == n:
            ans = l + 1
            break
    else:
        if i not in r:
            r[i] = 1
        else:
            r[i] += 1
        if r[i] == n:
            ans = l + 1
            break
    if j != 0:
        if j not in c:
            c[j] = 1
        else:
            c[j] += 1
        if c[j] == n:
            ans = l + 1
            break
    else:
        if n not in c:
            c[n] = 1
        else:
            c[n] += 1
        if c[n] == n:
            ans = l + 1
            break
    if ai % (n+1) == 1:
        d[1] += 1
        if d[1] == n:
            ans = l + 1
            break
    b = ai - n
    if b % 2 == 0 and b // 2 >= 0 and b // 2 < n:
        d[2] += 1
        if d[2] == n:
            ans = l + 1
            break
    # print(ans)

# print(r)
# print(c)
# print(d)

print(ans)
