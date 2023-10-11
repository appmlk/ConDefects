n, m = map(int, input().split())
l = list(map(int, input().split()))

def search(width: int) -> bool:
    length = 0
    lines = 1
    for e in l:
        if length > 0:
            length += 1
        if length + e > width:
            lines += 1
            length = 0
            count = 0
        length += e
    # print(width, lines)
    return lines <= m

ok = 1 << 60
ng = max(l)

while abs(ok - ng) > 1:
    mid = (ok + ng) // 2
    if search(mid):
        ok = mid
    else:
        ng = mid
print(ok)