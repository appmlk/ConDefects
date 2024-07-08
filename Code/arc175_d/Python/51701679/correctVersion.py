#ARC175D LIS on Tree 2

#入力受取
N, K = map(int, input().split())
G = [[] for _ in range(N)]
for _ in range(N - 1):
    u, v = map(lambda x: int(x) - 1, input().split())
    G[u].append(v)
    G[v].append(u)

#深さを割り当て
D = [1] * N
Q = [(0, -1, 1)]
for now, back, d in Q:
    D[now] = d
    for nxt in G[now]:
        if nxt != back:
            Q.append((nxt, now, d + 1))

#持ち点を計算  この時点で構築可否を判定
cost = sum(D)
if K not in range(N, cost + 1):
    exit( print('No') )
else:
    print('Yes')

#A[d]: 深さがdである頂点の一覧
#B[d]: 深さがdである頂点の個数 の累積和
M = max(D)
A = [[] for _ in range(M + 1)]
for i, d in enumerate(D):
    A[d].append(i)
B = [0] * (M + 2)
for d in range(1, M + 1):
    B[d + 1] = B[d] + len(A[d])

#前から加算(1)  Pに値を割り当て
P = [0] * N
diff = K
for d in range(1, M + 1):
    cnt = B[-1] - B[d]  #値がd以上の頂点数
    if cnt <= diff:
        diff -= cnt
        for i in A[d]:
            P[i] = d
        continue
    elif 0 <= diff < cnt:
        #後ろからdiff個をdに、それ以外を値d - 1に割り振る
        Q = []
        for e in range(d, M + 1):
            Q.extend(A[e])
        while Q:
            i = Q.pop()
            if diff:
                P[i] = d
                diff -= 1
            else:
                P[i] = d - 1
        break
assert all(P[i] <= D[i] for i in range(N))
assert sum(P) == K

#割り当て
Q = sorted((P[i], - D[i], i) for i in range(N))
ans = [0] * N
for c, (_, _, i) in enumerate(Q, start = 1):
    ans[i] = c
print(*ans)
