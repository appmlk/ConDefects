N = int(input())
grid = [[0 for _ in range(N)] for j in range(N)]
grid[(N+1)//2-1][(N+1)//2-1] = "T"
dir=[[0,1],[1,0],[0,-1],[-1,0]]
dirPath = 0
x=y=0
grid[x][y] = 1
for i in range(2,N**2):
    while x+dir[dirPath][0] < 0 or x+dir[dirPath][0] > N-1   or y+dir[dirPath][1] < 0 or y+dir[dirPath][1] > N-1 or grid[x+dir[dirPath][0]][y+dir[dirPath][1]] != 0 :
        dirPath = (dirPath+1)%4
    x = x + dir[dirPath][0]
    y = y + dir[dirPath][1]
    # print(x,y)

    grid[x][y] = i

for i in range(N):
    print(*grid[i])
