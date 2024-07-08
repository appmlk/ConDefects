def solve(n, m, a, b):
    # 対角要素との列の位置ズレが異なるm種を用意
    S = set([(i+j)%n for i, j in zip(a, b)])
    for i in range(n):
        if len(S) == m:
            break
        S.add(i)

    # ズレごとのマスを埋めていく 
    ans = []
    for d in S:
        for i in range(n):
            j = (d - i) % n
            ans.append((i+1, j+1))
    return ans

n, m = map(int, input().split())
a, b = zip(*[map(int, input().split()) for i in range(m)])
ans = solve(n, m, a, b)

print(len(ans))
for i, j in ans:
    print(i, j)
