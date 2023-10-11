N, M = map(int, input().split())
if N > M:
    r = N
    N = M
    M = r
    swap = 1
else:
    swap = 0
ans = [(1, 1)]
for i in range(1, N - 1, 2):
    for j in range(3):
        for k in range(3):
            if j + k == 0:
                continue
            ans.append((i + j, i + k))
if N & 1:
    for i in range(N + 1, M + 1):
        ans.append((N, i))
else:
    ans.append((N - 1, N))
    if N + 1 <= M:
        ans.append((N - 1, N + 1))
    for i in range(N - 1, M + 1):
        ans.append((N, i))
print(len(ans))
for u, v in ans:
    if swap:
        print(v, u)
    else:
        print(u, v)
X3_Y = set()
X_Y3 = set()
for u, v in ans:
    assert 1 <= u <= N and 1 <= v <= M
    X3_Y.add(u * 3 + v)
    X_Y3.add(u + v * 3)
assert len(X3_Y) == len(ans) and len(X_Y3) == len(ans), f'{X_Y3}, {X_Y3}'
if (N & 1) or M > N:
    assert len(ans) == N * 3 + M - 3
else:
    assert len(ans) >= N * 3 + M - 3 - 1
    # if N > 4:
    #     assert len(ans) >= N * 3 + M - 3
