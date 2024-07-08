import sys
input = sys.stdin.readline
from itertools import *

def check(S):
    ss = list(zip(*S))
    for i, s in enumerate(ss):
        if s.count('.') != 2: return False
        if s.count('A') != 1: return False
        if s.count('B') != 1: return False
        if s.count('C') != 1: return False
        for ns in s:
            if ns != '.': break
        if C[i] != ns: return False
    return True
    

def dfs(i, P):
    if i == N:
        if check(S):
            print('Yes')
            for s in S:
                print(*s, sep='')
            exit()
        return
    for npi in range(lp):
        if npi in seen: continue
        for np in P[npi]:
            if np != '.': break
        if R[i] != np: continue
        S[i] = P[npi]
        seen.add(npi)
        dfs(i + 1, P)
        seen.discard(npi)


N = int(input())
R = input()
C = input()

T = 'ABC' + '.' * (N - 3)
P = set()
for p in permutations(T):
    P.add(p)

# P = list(permutations(T))
lp = len(P)
P = list(P)
S = ['.' * N for _ in range(N)]
seen = set()
dfs(0, P)
print('No')