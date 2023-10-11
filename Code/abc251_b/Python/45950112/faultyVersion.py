n, w = map(int, input().split())
an = list(map(int, input().split()))

an.append(0)
an.append(0)
an.append(0)
ans = set()
n += 3
for i in range(n):
    for j in range(n):
        for k in range(n):
            total = an[i] + an[j] + an[k]
            if total != 0 and total < w and i != j and i != k and j != k:
                ans.add(total)
print(len(ans))