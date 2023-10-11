import sys
from collections import Counter

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


T = int(readline())
for _ in range(T):
    N, K = map(int, readline().split())
    S = readline().rstrip()
    S += '*'

    cnt_one = S.count('1')
    C = Counter(S[:K])
    cnt = 0

    for i in range(N - K + 1):
        if C['1'] == cnt_one and C['0'] == 0:
            cnt += 1

        C[S[i]] -= 1
        C[S[i + K]] += 1
    
    if cnt == 1:
        print('Yes')
    else:
        print('No')
