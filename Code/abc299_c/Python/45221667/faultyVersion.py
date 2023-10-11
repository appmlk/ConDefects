import sys
f = sys.stdin

N = int(f.readline().rstrip())
S = f.readline().rstrip()

d, td = 0, 0
s = 0
for i in range(N):
    if S[i] == 'o':
        td += 1
    else:
        s = 1
        d = max(d, td)
        td = 0

print(d if (0 < d and s == 1) else -1)
