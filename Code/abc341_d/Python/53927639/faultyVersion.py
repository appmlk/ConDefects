from math import lcm


def count_divisible_by_either(x, n, m):
    cnt_n = x // n
    cnt_m = x // m
    cnt_d = x // lcm(n, m)

    return cnt_n + cnt_m - cnt_d * 2


n, m, k = map(int, input().split())

l = 1
r = 10**18

while r - l > 1:
    mid = (l + r) // 2
    cnt = count_divisible_by_either(mid, n, m)
    # print(l, r, mid, cnt)
    if cnt >= k:
        r = mid
    else:
        l = mid


print(r)
