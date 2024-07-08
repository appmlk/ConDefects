from sys import stdin
from sys import setrecursionlimit
from heapq import *
setrecursionlimit(10**8)
input = lambda : stdin.readline().strip()

N = int(input())
A = list(map(int, input().split()))
thp = [[(0, -1)] for i in range(4 * N)]
delt = set()

def add(i, x, y, l, r, info):
    if l >= y or r <= x:
        return
    if l >= x and r <= y:
        # print(i, l, r)
        heappush(thp[i], info)
        return
    mid = l + r >> 1
    add(i << 1, x, y, l, mid, info)
    add(i << 1 | 1, x, y, mid, r, info)

def query(i, x, l, r):
    while thp[i][0][1] in delt:
        heappop(thp[i])
    res = -thp[i][0][0]
    if r - l == 1:
        return res
    mid = l + r >> 1
    if mid > x:
        res = max(res, query(i << 1, x, l, mid))
    else:
        res = max(res, query(i << 1 | 1, x, mid ,r))
    return res

q = int(input())
for i in range(q):
    op = list(map(int, input().split()))
    if op[0] == 1:
        l, r, x = op[1:]
        l -= 1
        # print('add:')
        add(1, l, r, 0, N, (-x, i))
    elif op[0] == 2:
        delt.add(op[1] - 1)
    else:
        x = op[1] - 1
        # print('query:')
        print(max(A[x], query(1, x, 0, N)))