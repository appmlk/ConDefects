import io,os
from copy import deepcopy
import heapq
input = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline



def getpattern(grid,d,sym):

    n = len(grid)    
    newgrid = [[0 for _ in range(n)] for _ in range(n)]

    ans = 0
    for i in range(n):
        for j in range(n):
            rest = grid[i][j] % (2*d)
            if (i+j)%2==sym:
                if rest <= d:   
                    newgrid[i][j] = grid[i][j] - rest
                    ans += abs(rest)
                else:
                    newgrid[i][j] = grid[i][j] + 2 * d - rest
                    ans += abs(2*d - rest)
            else:
                newgrid[i][j] = grid[i][j] + d - rest
                ans += abs(d-rest)


    return ans, newgrid

    
              
 
    
def main():


    n,d = map(int,input().split())
    grid = []

    for i in range(n):
        temp = list(map(int,input().split()))
        grid.append(temp)




    ans1, grid1 = getpattern(grid,d,0)
    ans2, grid2 = getpattern(grid,d,1)

#    print(min(ans1,ans2))
    if ans1 < ans2:
        for ele in grid1:  print(*ele)
    else:
        for ele in grid2:  print(*ele)

    

 


    
            
    



















main()
