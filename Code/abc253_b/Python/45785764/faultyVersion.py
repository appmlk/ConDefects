h, w = map(int, input().split())
s = []
o = []
for _ in range(h):
    s.append(input())
for row in range(h):
    for col in range(w):
        if s[row][col] == "o":
            o.append([row,col])
hirai = abs(o[0][0]- o[1][0]) + abs(o[1][0]-o[1][1])
print(hirai)