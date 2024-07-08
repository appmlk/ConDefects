H, W, N = map(int,input().split())

grid = []
for i in range(H):
    grid.append(["."] * W) 

i = 0
j = 0
p = 0
h = [[0,-1],[1,0],[0,1],[-1,0]]

for k in range(N):
    if grid[i][j] == ".":
        grid[i][j] = "#"
        # 時計まわり
        p = (p+1) % 4

    elif grid[i][j] == "#":
        grid[i][j] = "."
        #　半時計まわり
        p = (p-1+4) % 4

    #座標更新
    i = (i+h[p][1]+H) % H
    j = (j+h[p][0]+W) % W
    

for t in range(H):
    print(*grid[t])