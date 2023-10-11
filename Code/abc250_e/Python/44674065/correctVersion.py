import sys
input = sys.stdin.readline
def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# ABC250 E 1421 - Prefix Equality
N = ip()
A = lmp()
B = lmp()
aset = set()
bset = set()
xorset = set()
# xorset: A, B どちらかだけに含まれる要素の集合
bp = 0
same = [False] * (N+1)
same[0] = True
for a in A:
    if a in aset:continue
    aset.add(a)
    if a in xorset:
        xorset.remove(a)
    else:
        xorset.add(a)
    while bp < N and B[bp] in bset:
        bp += 1
    if bp == N:
        break
    b = B[bp]
    bset.add(b)
    if b in xorset:
        xorset.remove(b)
    else:
        xorset.add(b)
    same[len(aset)] = (len(xorset) == 0)
    # print(aset, bset, xorset, same)
cntA = [0] * (N+1)
cntB = [0] * (N+1)
# cntA[i], cntB[i]: i番目までに含まれる要素数
aset = set()
bset = set()
for i in range(N):
    aset.add(A[i])
    cntA[i+1] = len(aset)
    bset.add(B[i])
    cntB[i+1] = len(bset)
# print(aset, bset, cntA, cntB)
Q = ip()
for _ in range(Q):
    x, y = mp()
    if cntA[x] == cntB[y] and same[cntA[x]]:
        print("Yes")
    else:
        print("No")