N = int(input())
time = [0] * (N+1)
need = [[] for _ in range(N+1)]
for i in range(1, N+1):
    t, k, *A = list(map(int, input().split()))
    time[i] = t
    need[i] = A

ans = 0
got = set()
stack = [N]
while stack:
    node = stack.pop()
    if node in got:
        continue
    got.add(node)
    ans += time[node]
    for n in need[node]:
        if n in got:
            continue
        stack.append(n)

print(ans)