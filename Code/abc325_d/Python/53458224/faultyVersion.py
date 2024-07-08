from heapq import heappush, heappop

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
arr = sorted(arr)
heap = []
i, cnt, t = 0, 0, 1
while i < len(arr):
	if t < arr[i][0]: t = arr[i][0]
	while i < len(arr) and arr[i][0] <= t:
		heappush(heap, (arr[i][0] + arr[i][1]))
		i += 1
	while len(heap):
		x = heappop(heap) 
		if x < t: continue
		t += 1
		cnt += 1
		break
while len(heap):
	if heappop(heap) >= t:
		t += 1
		cnt += 1
print(cnt)