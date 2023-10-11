import math

def distance_2d(x1, y1, x2, y2):
    return math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

n = int(input())
l = [0]*n
ans = [[0 for _ in range(n)] for _ in range(n)]
for i in range(n):
    l[i]=list(map(int, input().split()))
    
for i in range(n):
    for j in range(n):
        ans[i][j] = distance_2d(l[i][0], l[i][1], l[j][0], l[j][1])
        
print(max(max(row) for row in ans))
