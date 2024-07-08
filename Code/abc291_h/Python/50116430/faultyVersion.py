class Centroid_decomposition:
    def __init__(self, N, graph):
        self._N = N
        self._C = C = [dict() for _ in range(N)]
        self._B = B = [list() for _ in range(N)]
        Q = [(0, -1)]
        S = [1] * N
        for now,back in Q:
            for nxt in G[now]:
                if nxt != back: Q.append((nxt, now))
        while Q:
            now,back = Q.pop()
            if back != -1: S[back] += S[now]

        #重心分解(再帰での実装例)
        def find(now):
            while True:  #1. 重心探索
                for nxt in G[now]:
                    if len(C[nxt]) == 0 and S[nxt] * 2 > S[now]:
                        S[now], S[nxt] = S[now] - S[nxt], S[now]
                        now = nxt
                        break
                else: break
            R = [(now, -1)]  #2. BFSで部分木を全探索
            while R:
                i,b = R.pop()
                C[now][i] = []
                for t in G[i]:
                    if len(C[t]) == 0 and b != t:
                        R.append((t, i))
                        C[now][i].append(t)
            if S[now] > 1:  #3. 部分木を重心分解
                for nxt in G[now]:
                    if len(C[nxt]) == 0:
                        find(nxt)
            for i in C[now]:  #4. 帰りがけにB: belongに反映
                B[i].append(now)

        #重心分解(非再帰での実装例)
        Q = [now]
        R = []
        while Q:
            now = Q.pop()
            if now >= 0:  #行きがけの処理
                Q.append(~now)
                while True:
                    for nxt in G[now]:
                        if len(C[nxt]) == 0 and S[nxt] * 2 > S[now]:
                            S[now], S[nxt] = S[now] - S[nxt], S[now]
                            now = nxt
                            break
                    else: break
                R.append((now, -1))
                while R:
                    i,b = R.pop()
                    C[now][i] = []
                    for t in G[i]:
                        if len(C[t]) == 0 and b != t:
                            R.append((t, i))
                            C[now][i].append(t)
                if S[now] > 1:
                    for nxt in G[now]:
                        if len(C[nxt]) == 0:
                            Q.append(nxt)
            else:  #帰りがけの処理
                now = ~now
                for i in C[now]:
                    B[i].append(now)



#入力受取
N = int(input())
G = [[] for _ in range(N)]
for _ in range(N - 1):
    a,b = map(int,input().split())
    G[a-1].append(b-1)
    G[b-1].append(a-1)

#重心分解 
C = Centroid_decomposition(N, G)

#親を探す
P = [-1] * N
for i in range(N):
    L = C._B[i]  #所属の一覧  左ほど小さい部分木
    if len(L) > 1:
        P[i] = L[1] + 1

#答えを出力
print(*P)
