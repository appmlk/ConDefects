def resolve():
    import sys

    input = sys.stdin.readline
    MOD = 998244353
    INF = float("inf")
    n = int(input())
    points = [tuple(map(int, input().split())) for _ in range(n)]
    s = tuple(map(int, input().split()))
    t = tuple(map(int, input().split()))
    edges = [[] for _ in range(n + 2)]
    is_direct = True
    for i in range(n):
        j = (i + 1) % n
        d = distance(points[j], points[i])
        edges[i].append((j, d))
        edges[j].append((i, d))
    for i in range(-1, n - 1):
        if not intersect(s, points[i], points[i - 1], points[i + 1]):
            edges[n].append((i % n, distance(s, points[i])))
        if not intersect(t, points[i], points[i - 1], points[i + 1]):
            edges[i % n].append((n + 1, distance(t, points[i])))
        if is_direct and intersect_seg(s, t, points[i - 1], points[i]):
            is_direct = False
    if is_direct:
        print(distance(s, t))
        return
    import heapq

    q = [(0.0, n)]
    reached = [False] * (n + 2)
    while q:
        d, i = heapq.heappop(q)
        if reached[i]:
            continue
        if i == n + 1:
            print(d)
            return
        reached[i] = True
        for j, k in edges[i]:
            if reached[j]:
                continue
            heapq.heappush(q, (d + k, j))


def distance(a, b) -> float:
    # 2点間のユークリッド距離
    return sum((i - j) ** 2 for i, j in zip(a, b)) ** 0.5


def intersect(p1, p2, p3, p4):
    # 直線p1p2と線分p3p4が重なっているか判定
    tc1 = (p1[0] - p2[0]) * (p3[1] - p1[1]) + (p1[1] - p2[1]) * (p1[0] - p3[0])
    tc2 = (p1[0] - p2[0]) * (p4[1] - p1[1]) + (p1[1] - p2[1]) * (p1[0] - p4[0])
    td1 = (p3[0] - p4[0]) * (p1[1] - p3[1]) + (p3[1] - p4[1]) * (p3[0] - p1[0])
    td2 = (p3[0] - p4[0]) * (p2[1] - p3[1]) + (p3[1] - p4[1]) * (p3[0] - p2[0])
    return tc1 * tc2 < 0 and td1 * td2 < 0


def intersect_seg(p1, p2, p3, p4):
    # 線分p1p2と線分p3p4が重なっているか判定
    return intersect(p1, p2, p3, p4) and intersect(p3, p4, p1, p2)


if __name__ == "__main__":
    resolve()
