def solve(n, m, a, b):
    # 0-indexへ変換
    a = [_-1 for _ in a]
    b = [_-1 for _ in b]

    # 対角要素とのズレがどれぐらいのパターンを使っているか
    S = set() # m個のパターンが必要
    for i, j in zip(a, b):
        S.add((i+j) % n)
    i = 0
    while len(S) < m:
        S.add(i)
        i += 1

    # パターンごとにn個のマスを埋められる(i+j = d mod nとなる(i,j)のペア) 
    ans = []
    for d in S:
        for i in range(n):
            # ズレが異なるパターン同士は重なり合うことはないので、解として追記していく
            j = (n - 1 - i + d) % n
            ans.append((i+1, j+1))
    return ans

n, m = map(int, input().split())
a, b = zip(*[map(int, input().split()) for i in range(m)])
ans = solve(n, m, a, b)
print(len(ans))
for i, j in ans:
    print(i, j)
