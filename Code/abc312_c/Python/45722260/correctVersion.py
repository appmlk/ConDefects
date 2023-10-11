N, M = map(int, input().split())

A = list(map(int, input().split()))
B = list(map(int, input().split()))


def is_ok(mid):
    if sum(map(lambda x: x <= mid, A)) >= sum(map(lambda x: x >= mid, B)):
        return True
    else:
        return False


def binary_search(ok, ng):
    while ng - ok > 1:
        mid = (ok + ng) // 2
        if is_ok(mid):
            ng = mid
        else:
            ok = mid
    return ng


print(binary_search(0, 1000000001))
