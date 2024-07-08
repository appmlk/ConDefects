import sys;sys.setrecursionlimit(100000000)
H,W,N=map(int,sys.stdin.readline().split())
grid=[["."]*W for i in range(H)]
directions=((-1,0),(0,1),(1,0),(0,-1))
dirNum=0
x=0
y=0
for i in range(N):
    if grid[y][x]==".":
        grid[y][x]="#"
        dirNum+=1
        y+=directions[dirNum%4][0]
        x+=directions[dirNum%4][1]
    else:
        grid[y][x]="."
        dirNum-=1
        y+=directions[dirNum%4][0]
        x+=directions[dirNum%4][1]
    if x==W:
        x=0
    elif x==-1:
        x=W-1
    if y==H:
        y=0
    elif y==-1:
        y=H-1
for clm in grid:
    print(*clm)