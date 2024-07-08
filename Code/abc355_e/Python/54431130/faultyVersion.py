from collections import defaultdict
import sys
#input = sys.stdin.readline
sys.setrecursionlimit(10**9)

INF = 10**9

N, L000, R000 = map(int, input().split())

R000 += 1

numss = [defaultdict(lambda: INF) for d in range(N+2)]
numss[0][(L000, R000)] = 0
prevss = [defaultdict(lambda: (-1, -1)) for d in range(N+2)]
for d in range(N+1):
    nums = numss[d]
#    print('\n##### d:', d, '/ nums:', nums)
    for (L, R), num in nums.items():
        L2s, R2s = [], []
        if L >= R:
            L2s.append((L, 0))
            R2s.append((R, 0))
        else:
            if (L>>d)&1:
                L2s.append((L + (1<<d), 1))
                L2s.append((L - (1<<d), 1))
            else:
                L2s.append((L, 0))
            if (R>>d)&1:
                R2s.append((R + (1<<d), 1))
                R2s.append((R - (1<<d), 1))
            else:
                R2s.append((R, 0))
        for L2, kL in L2s:
            for R2, kR in R2s:
                num2 = num + kL + kR
#                if L2 >= R2:
#                    L2, R2 = 0, 0
                if num2 < numss[d+1][(L2, R2)]:
                    numss[d+1][(L2, R2)] = num2
                    prevss[d+1][(L2, R2)] = (L, R)
#print('# numss[N+1]:', numss[N+1])

minNum = INF
Lmin, Rmin = -1, -1
for (L, R), num in numss[N+1].items():
    if num < minNum:
        minNum = num
        Lmin, Rmin = L, R
#print('# minNum:', minNum, '/ (Lmin, Rmin):', (Lmin, Rmin))

LRSs = []
L, R = Lmin, Rmin
for d in reversed(range(1, N+2)):
    L0, R0 = prevss[d][(L, R)]
#    print('# d:', d, '/ (L, R):', (L, R), '/ (L0, R0):', (L0, R0))
    if L0 < L:
        LRSs.append((L0, L, 1))
    elif L0 > L:
        LRSs.append((L, L0, -1))
    if R0 < R:
        LRSs.append((R0, R, -1))
    elif R0 > R:
        LRSs.append((R, R0, 1))
    L, R = L0, R0
#print('# LRSs:', LRSs)

ans = 0
for L, R, S in LRSs:
#    R += 1
    d = abs(R-L)
    i = d.bit_length() - 1
    j = L//d
    print('?', i, j)
    T = int(input())
    if T == -1:
        sys.exit()
    ans += T*S
    ans %= 100

print('!', ans)
