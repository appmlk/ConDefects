import sys
input = sys.stdin.buffer.readline
sr = lambda: input().rstrip().decode('ascii') # 文字列の読み込み
ir = lambda: int(input()) # 数字の読み込み
lr = lambda: list(map(int, input().split())) # 数字の配列の読み込み

N, M = lr()
p = lr()
tree = [set() for _ in range(N)]
for i in range(1, N):
    tree[p[i-1]-1].add(i)
ins = [-1]*N
for i in range(M):
    x, y = lr()
    ins[x-1] = max(ins[x-1], y)
q = [(0, ins[0])]
visit = [0]*N
cnt = 1 if ins[0] >= 0 else 0
while len(q):
    s, i = q.pop()    
    for v in tree[s]:
        if not visit[v]:
            visit[v] = 1
            ni = max(ins[v], i-1)
            if ni >= 0:
                cnt += 1
            q.append((v, ni))
print(cnt)