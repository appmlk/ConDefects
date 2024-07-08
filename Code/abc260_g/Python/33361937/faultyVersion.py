import bisect, collections, copy, heapq, itertools, math, string
import random
import sys
def I(): return int(sys.stdin.readline().rstrip())
def MI(): return map(int, sys.stdin.readline().rstrip().split())
def LI(): return list(map(int, sys.stdin.readline().rstrip().split()))
def S(): return sys.stdin.readline().rstrip()
def LS(): return list(sys.stdin.readline().rstrip().split())
from collections import deque
from collections import Counter
from collections import defaultdict
import bisect
from functools import reduce

def main():
    N,M = MI()
    imos_grid = [[0 for _ in range(max(N + M + 3, N + 3))] for _ in range(max(N + 2 * M + 3, 3 * N + 3))]
    look_cnt_dic = defaultdict(int)

    def imos_dist(x,y):
        imos_grid[y][x] += 1
        imos_grid[y + 2][x - 1] -= 1
        imos_grid[y + 2 * M + 2][x - 1] += 1
        imos_grid[y + 2 * M + 2][x] -= 1
        imos_grid[y + 2][x + M] += 1
        imos_grid[y][x + M] -= 1



    for i in range(N):
        T = S()
        for j,t in enumerate(T):
            if t == 'O':
                imos_dist(i + 1, j + 1)

    # for grid in imos_grid:
    #     print(grid)

    for i in range(3 * N + 3):
        for j in range(N + 3):
            if i - 2 >= 0 and j + 1 <= N + M + 2:
                imos_grid[i][j] += imos_grid[i - 2][j + 1]
    #print('a')
    for j in range(N + 3):
        for i in range(1, 3 * N + 3):
            imos_grid[i][j] += imos_grid[i - 1][j]
    for i in range(3 * N + 3):
        for j in range(1, N + 3):
            imos_grid[i][j] += imos_grid[i][j - 1]

    # for grid in imos_grid:
    #     print(grid)
    Q = I()
    for i in range(Q):
        x, y = MI()
        print(imos_grid[y][x])



if __name__ == "__main__":
    main()
