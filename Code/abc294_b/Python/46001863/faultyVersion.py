H, W = map(int, input().split())
A = []
for _ in range(H):
    A.append(list(map(int, input().split())))

strings = []
for i in range(H):
    s = ""
    for j in range(W):
        if A[i][j] == 0:
            s += "."
        else:
            s += chr(ord('A') + A[i][j] + 1)
    strings.append(s)

for s in strings:
    print(s)

