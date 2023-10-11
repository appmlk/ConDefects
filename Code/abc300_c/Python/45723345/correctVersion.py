H,W = map(int,input().split())
C = []
for i in range(H):
    C.append(list(input()))

def check(y,x):
    size = 0
    while True:
        if  (y + (size + 1) < H and x + (size + 1) < W and C[y+(size+1)][x+(size+1)] == '#') and \
            (y + (size + 1) < H and x - (size + 1) >= 0 and C[y+(size+1)][x-(size+1)] == '#') and \
            (y - (size + 1) >= 0 and x + (size + 1) < W and C[y-(size+1)][x+(size+1)] == '#') and \
            (y - (size + 1) >= 0 and x - (size + 1) >= 0 and C[y-(size+1)][x-(size+1)] == '#'):
            size += 1
        else:
            break
    ans[size] += 1

ans = [0 for _ in range(min(H,W)+1)]

for i in range(H):
    for j in range(W):
        if C[i][j] == '#':
            check(i,j)

print(*ans[1:])