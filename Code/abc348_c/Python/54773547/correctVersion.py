N = int(input())
AC = sorted(sorted([list(map(int, input().split())) for _ in range(N)]), key=lambda x:x[1])
_min, num = AC[0]
for i in AC[1:]:
    if num != i[1]:
        num = i[1]
        if _min < i[0]:
            _min = i[0]
print(_min)