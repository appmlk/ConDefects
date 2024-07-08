N = int(input())
AC = sorted([[*list(map(int, input().split())), i+1] for i in range(N)], key=lambda x:x[1])
_max = AC[0][0]
not_discarded = [AC[0][2]]
for i in AC:
    if _max < i[0]:
        not_discarded.append(i[2])
        _max = i[0]
print(len(not_discarded))
print(*sorted(not_discarded))