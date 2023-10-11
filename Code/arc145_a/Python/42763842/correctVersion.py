import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


N = int(readline())
S = readline().rstrip()

if (S[0] == 'A' and S[-1] == 'B') or S == 'BA':
    print('No')
else:
    print('Yes')
