H, W = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(H)]

Flag = True
for a in range(H-1):
	for b in range(a+1, H):
		for c in range(W-1):
			for d in range(b+1, W):
				if A[a][c] + A[b][d] > A[b][c] + A[a][d]:
					Flag = False
if Flag:
	print('Yes')
else:
	print('No')