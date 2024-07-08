n = int(input())


def calc(num):
    moji = str(num)
    keta = len(moji)
    ret = 0
    for i in range(keta):
        p = int(moji[i])
        if i == 0:
            d = max(p - 1, 0)
            ret = ret + d * 9 ** (keta - i - 1)
        else:
            d = p + 1
            if d-1 > int(moji[i - 1]):
                d = d - 1
            elif d-1 == int(moji[i - 1]):
                ret = ret + (d-1) * 9 ** (keta - i - 1)
                for i in range(1, keta):
                    ret += 9 ** i
                return ret
            d = max(d - 1, 0)
            ret = ret + d * 9 ** (keta - i - 1)
    for i in range(1, keta):
        ret += 9 ** i
    return ret + 1


for _ in range(n):
    num = int(input())
    l = 0
    r = 10 ** 16
    md = (l + r) // 2
    while l + 1 < r:
        ret = calc(md)
        if ret >= num:
            r = md
        else:
            l = md
        md = (l + r) // 2
    print(r)


