# import系 ---
from collections import defaultdict

# 入力用 ---
INT = lambda: int(input())
MI = lambda: map(int, input().split())
MI_DEC = lambda: map(lambda x: int(x) - 1, input().split())
LI = lambda: list(map(int, input().split()))
LI_DEC = lambda: list(map(lambda x: int(x) - 1, input().split()))
LS = lambda: list(input())
LSS = lambda: input().split()

# コード ---
N, M = MI()
A = LI()

sets = [set() for _ in range(N)]

for i, a in enumerate(A, start=1):
    for j in range(a % i, N, i):
        if j - a < 0:
            break
        sets[j].add((j - a) // i)

for i in range(1, M+1):
    is_ok = False
    for j in range(N):
        if i not in sets[j]:
            print(j)
            is_ok = True
            break
    if not is_ok:
        print(N)
