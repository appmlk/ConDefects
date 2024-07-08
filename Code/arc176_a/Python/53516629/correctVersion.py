N, M = map(int, input().split())
S = set()
for _ in range(M):
    a, b = map(int, input().split())
    a -= 1; b -= 1
    S.add((a+b)%N)
T = M - len(S)

for i in range(N):
    if T == 0: break
    if not i in S:
        T -= 1
        S.add(i)

ret = []
for i in S:
    for j in range(N):
        ret.append((j, (i - j) % N))

print(len(ret))
for u, v in ret:
    print(u+1, v+1)
