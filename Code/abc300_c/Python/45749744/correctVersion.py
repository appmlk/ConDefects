# Copyright (c) 2023, Le Duc Phuc Long

# If you don't think twice, you have to code twice.

# Import session
import sys
#input = sys.stdin.readline
from collections import defaultdict

############ ---- Input Functions ---- ############
def inp():
    return int(input())

def inlt():
    return list(map(int, input().split()))

def instr():
    return list(input().strip())

def invr():
    return map(int, input().split())

############ ---- Other Functions ---- ############
# Precompute

# IO
#input = sys.stdin.readline
# sys.stdin = open('in.txt', 'r')
# sys.stdout = open('out.txt', 'w')
# Main function
n, m = invr()
mtx = [list(input()) for _ in range(n)]

def dfs(x, y):
    mtx[x][y] = '.'
    cnt = 1
    for i in range(-1, 2):
        for j in range(-1, 2):
            u, v = x+i, y+j
            if 0 <= u < n and 0 <= v < m and mtx[u][v] == '#':
                cnt += dfs(u, v)
    return cnt
                

ans = [0]*(min(n, m)+1)
for i in range(n):
    for j in range(m):
        if (mtx[i][j] == '#'):
            ans[dfs(i, j)//4] += 1

ans.pop(0)

print(' '.join(map(str, ans)))
