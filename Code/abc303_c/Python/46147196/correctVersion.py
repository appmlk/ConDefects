N, M, H, K = map(int, input().split())
S = input()
RLUD = {'R': (1, 0), 'L': (-1, 0), 'U': (0, 1), 'D': (0, -1)}

healing = set()
for _ in range(M):
    x, y = map(int, input().split())
    healing.add((x, y))

x, y = 0, 0
for s in S:
    H -= 1
    if H < 0:
        print('No')
        exit()
    x += RLUD[s][0]
    y += RLUD[s][1]
    if (x, y) not in healing:
        continue
    if H < K:
        healing.remove((x, y))
        H = K
print('Yes')
