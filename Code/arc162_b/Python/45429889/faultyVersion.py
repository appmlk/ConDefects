n = int(input())
p = list(map(int, input().split()))
ans = []
j = 0
while j < n:
    if p[j] != j + 1 and j < n - 2:
        ind = p.index(j + 1)
        if ind == n - 1:
            ans.append((n - 1, n - 3))
            p = p[: n - 3] + [p[n - 1]] + p[n - 3 : n - 1]
        else:
            ans.append((ind + 1, j))
            p = p[:j] + p[ind : ind + 2] + p[j:ind] + p[ind + 2 :]
            j += 1
    else:
        j += 1
if p[-1] != n:
    print("No")
else:
    print("Yes")
    print(len(ans))
    for a in ans:
        print(*a)
