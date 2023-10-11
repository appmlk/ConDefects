from bisect import bisect_left


def check(k):
    x = bisect_left(a, k + 0.5)
    y = m - bisect_left(b, k)
    return x >= y


n, m = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
a.sort()
b.sort()
ng = -1
ok = 10**10

while abs(ok - ng) > 1:
    mid = ok + ng >> 1
    if check(mid):
        ok = mid
    else:
        ng = mid

print(ok)
