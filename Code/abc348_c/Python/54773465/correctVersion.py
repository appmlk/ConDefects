import collections
N = int(input())
AC = [list(map(int, input().split())) for _ in range(N)]
AC = sorted(AC)
AC = sorted(AC, key=lambda x:x[1])
num = AC[0][1]
_min = AC[0][0]
for i in AC[1:]:
    if num != i[1]:
        num = i[1]
        if _min < i[0]:
            _min = i[0]
print(_min)