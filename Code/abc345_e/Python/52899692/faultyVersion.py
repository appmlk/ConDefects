# cf. https://atcoder.jp/contests/abc345/editorial/9580
INF = 10**10

def solve(n, k, c, v):
    C1 = [-1]*(k+1) # 最大の価値になる色
    V1 = [-INF]*(k+1) # 最大の価値
    C2 = [-1]*(k+1) # 次善の価値になる色
    V2 = [-INF]*(k+1) # 次善の価値
    C1[0] = 0
    V1[0] = 0

    for ci, vi in zip(c, v):
        for j in range(k, 0, -1):
            # 最大価値の更新
            if C1[j] != ci:
                C1[j] = ci
                V1[j] += vi
            else:
                C1[j] = ci
                V1[j] = V2[j] + vi

            # 次点の価値のリセット
            C2[j] = -1
            V2[j] = -INF

            # 以前のアイテムと比較して更新
            for C, V in [(C1, V1), (C2, V2)]:
                if V[j-1] >= V1[j]:
                    if C[j-1] != C1[j]:
                        C2[j] = C1[j]
                        V2[j] = V1[j]
                    C1[j] = C[j-1]
                    V1[j] = V[j-1]
                elif V[j-1] >= V2[j] and C[j-1] != C1[j]:
                    C2[j] = C[j-1]
                    V2[j] = V[j-1]
        
        if C1[0] != ci:
            C1[0] = ci
            V1[0] += vi
        else:
            V1[0] = -INF

    return -1 if V1[k] < 0 else V1[k]

n, k = map(int, input().split())
c, v = zip(*[map(int, input().split()) for i in range(n)])
print(solve(n, k, c, v))

