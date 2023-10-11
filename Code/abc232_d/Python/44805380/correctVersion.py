h, w = map(int, input().split())
c =  [input() for i in range(h)]

s = [[0] * w for i in range(h)]
s[0][0] = 1

ans = 1

for i in range(h):
    for j in range(w):
        if c[i][j] == '#' or i == j == 0:
            continue

        if i == 0 and j > 0:
            s[i][j] = s[i][j-1]+1
        elif i > 0 and j == 0:
            s[i][j] = s[i-1][j]+1
        else:
            s[i][j] = max(s[i][j-1], s[i-1][j])+1
        
        if s[i][j] == 1:
            s[i][j] = 0

        if ans < s[i][j]:
             ans = s[i][j]

print(ans)