N, M, L = map(int, input().split())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

pat = [list(map(lambda x:int(x)-1, input().split())) for _ in range(L)]
ng_set = set()
for i, j in pat:
    ng_set.add((i, j))

#P:Bを価格の大きい順にソートしたものでindexも同時に保持している
P = sorted([(b, j) for j, b in enumerate(B)], reverse=True)

ans = -1
for i, a in enumerate(A):
    for b, j in P:
        if (i, j) in pat:
            continue
        ans = max(ans, a+b)
        break

print(ans)
