n, k = map(int, input().split())
ab = [list(map(int, input().split())) for _ in range(n)]


def calc(num):
    global ab
    res = 0
    for j in range(n):
        if ab[j][0] <= num:
            continue
        res += ab[j][1]
    return res


l = -1
r = 9999999999999999
m = 0
while r > l + 1:
    m = (r + l) // 2
    if calc(m) < k:
        r = m
    else:
        l = m
print(r + 1)
