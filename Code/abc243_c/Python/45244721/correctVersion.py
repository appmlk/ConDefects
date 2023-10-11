n = int(input())
xy = [list(map(int, input().split())) for _ in range(n)]
s = input()

y = {}
for i in range(n):
    xi, yi = xy[i]
    y.setdefault(yi, [])
    y[yi].append([xi, s[i]])

ans = 'No'
for i in y:
    yi = sorted(y[i])
    for j in range(len(yi)-1):
        if yi[j][1] == 'R' and yi[j+1][1] == 'L':
            ans = 'Yes'
print(ans)