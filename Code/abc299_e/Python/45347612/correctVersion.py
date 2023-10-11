from collections import deque
import sys
N, M = map(int, input().split())
relations = [[] for _ in range(N)]
color = ["u"] * N
for _ in range(M):
    u, v = map(int, input().split())
    relations[u-1].append(v-1)
    relations[v-1].append(u-1)
K = int(input())
infos = [None] * K
for i in range(K):
    infos[i] = list(map(int, input().split()))
infos = sorted(infos, key=lambda x:x[1], reverse=True)

for p, d in infos:
    queue = deque([(p-1, d)])
    arrived = set()
    while queue:
        crt_p, crt_d = queue.popleft()
        arrived.add(crt_p)
        if crt_d >= 1:
            color[crt_p] = "w"
            for next_p in relations[crt_p]:
                if next_p not in arrived:
                    queue.append((next_p, crt_d-1))
for p, d in infos:
    queue = deque([(p-1, d)])
    arrived = set()
    tmp_ans = False
    while queue:
        crt_p, crt_d = queue.popleft()
        arrived.add(crt_p)
        if crt_d > 0:
            for next_p in relations[crt_p]:
                if next_p not in arrived:
                    queue.append((next_p, crt_d-1))
        elif crt_d == 0:
            if color[crt_p] == "u":
                tmp_ans = True
    if not tmp_ans:
        print("No")
        sys.exit()

print("Yes")
ans = []
for c in color:
    if c == "u":
        ans.append("1")
    else:
        ans.append("0")
ans = "".join(ans)
print(ans)