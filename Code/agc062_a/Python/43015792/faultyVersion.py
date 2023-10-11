import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


T = int(readline())

for _ in range(T):
    N = int(readline())
    S = readline().rstrip()

    if not 'B' in S:
        print('A')
        continue

    index_ = S.index('B')
    if S == 'A' * index_ and 'B' * (N - index_):
        print('B')
    else:
        print('A')
