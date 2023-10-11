def check(aaa):
    """ A0とA1に対する操作回数の差分 d を求める """
    bbb = aaa.copy()
    bbb.append(aaa[0])

    for i in range(2, n):
        tmp = bbb[i - 1]
        bbb[i - 1] -= tmp
        bbb[i] += tmp * 2
        bbb[i + 1] -= tmp

    if bbb[-2] + bbb[-1] != 0:
        return None

    d, m = divmod(bbb[-2], len(aaa))
    if m != 0:
        return None

    return d


def check2(aaa, s, d):
    bbb = aaa.copy()
    bbb.append(aaa[0])

    bbb[-2] -= s
    bbb[-1] += s * 2 - (s - d)
    bbb[0] += s * 2 - (s - d)
    bbb[1] += (s - d) * 2 - s
    bbb[2] -= (s - d)

    cnt = s + s - d
    for i in range(2, n):
        tmp = bbb[i - 1]
        if tmp < 0:
            return False, 0
        cnt += tmp
        bbb[i - 1] -= tmp
        bbb[i] += tmp * 2
        bbb[i + 1] -= tmp

    return True, cnt


n = int(input())
aaa = list(map(int, input().split()))

d = check(aaa)
if d is None:
    print(-1)
    exit()

if d < 0:
    aaa.reverse()
    aaa = aaa[-2:] + aaa[:-2]
    d *= -1

l = d - 1
r = 1 << 30
while l + 1 < r:
    m = (l + r) // 2
    res, cnt = check2(aaa, m, d)
    if res:
        r = m
    else:
        l = m

_, ans = check2(aaa, r, d)

print(ans)
