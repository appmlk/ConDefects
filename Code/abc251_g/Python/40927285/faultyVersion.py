def g_intersection_of_polygons(INF=float('inf')):
    N = int(input())
    Points = [[int(col) for col in input().split()] for row in range(N)]
    Points.append(Points[0])
    M = int(input())
    Moves = [[int(col) for col in input().split()] for row in range(M)]
    Q = int(input())
    Queries = [complex(*[int(col) for col in input().split()])
               for row in range(Q)]

    def cross(p, q, r, s):
        return p * s - q * r

    Q1, Q2, R = [0] * (N + 1), [0] * (N + 1), [-INF] * (N + 1)
    for i in range(N):
        delta_x = Points[i + 1][0] - Points[i][0]
        delta_y = Points[i + 1][1] - Points[i][1]
        Q1[i] = delta_x
        Q2[i] = delta_y
        for j in range(M):
            moved_x = Points[i][0] + Moves[j][0]
            moved_y = Points[i][1] + Moves[j][1]
            R[i] = max(R[i], cross(delta_x, delta_y, moved_x, moved_y))

    ans = []
    for q in Queries:
        a, b = q.real, q.imag
        for i in range(N):
            if cross(Q1[i], Q2[i], a, b) < R[i]:
                ans.append('No')
                break
        else:
            ans.append('Yes')
    return '\n'.join(map(str, ans))

print(g_intersection_of_polygons())