import numpy as np

# 点の数を入力
N = int(input("点の数を入力してください: "))

# 各点の座標を入力
points = np.array([list(map(float, input().split())) for _ in range(N)])

# 距離を計算する関数
def calculate_distance(point1, point2):
    return np.linalg.norm(point1 - point2)

for i in range(N):
    xi, yi = points[i]
    far_d, far_id = -1, -1
    for j in range(N):
        if i == j: continue
        xj, yj = points[j]
        d = calculate_distance(points[i], points[j])
        if d > far_d:
            far_d, far_id = d, j + 1
    print(far_id)
