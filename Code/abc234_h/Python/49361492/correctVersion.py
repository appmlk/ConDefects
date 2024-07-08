from scipy.spatial import KDTree

N, K = map(int, input().split())
points = []

for _ in range(N):
	x, y = map(int, input().split())
	points.append((x, y))

if N <= 5000:
	ans = []
	for i in range(N):
		for j in range(N):
			if i < j and (points[i][0]-points[j][0])**2 + (points[i][1]-points[j][1])**2 <= K**2:
				ans.append((i, j))
	print(len(ans))
	for (i, j) in ans:
		print(i+1, j+1)
else:
	kd_tree = KDTree(points)
	ans = sorted(kd_tree.query_pairs(r=K))
	print(len(ans))
	for (i, j) in ans:
		print(i+1, j+1)