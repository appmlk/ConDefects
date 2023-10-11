import sys
input = lambda: sys.stdin.readline().strip()

h, w = map(int, input().split())
rc = [input() for _ in range(h)]
snuke = 'snuke'

# 横
for i in range(h):
    for j in range(w-4):
        if rc[i][j:j+5] == snuke:
           for k in range(j, j+5):
               print(i+1, k+1)
        if ''.join(list(reversed(rc[i][j:j+5]))) == snuke:
            for k in reversed(range(j, j+5)):
                print(i+1, k+1)

# 縦
for i in range(w):
    for j in range(h-4):
        if rc[j][i]+rc[j+1][i]+rc[j+2][i]+rc[j+3][i]+rc[j+4][i] == snuke:
            for k in range(5):
                print(j+k+1, i+1)
        if ''.join(list(reversed(rc[j][i]+rc[j+1][i]+rc[j+2][i]+rc[j+3][i]+rc[j+4][i]))) == snuke:
            for k in reversed(range(5)):
                print(j+k+1, i+1)

# 右下斜め
for i in range(h-4):
    for j in range(w-4):
        if rc[i][j]+rc[i+1][j+1]+rc[i+2][j+2]+rc[i+3][j+3]+rc[i+4][j+4] == snuke:
            for k in range(5):
                print(i+k+1, j+k+1) 
        if ''.join(list(reversed(rc[i][j]+rc[i+1][j+1]+rc[i+2][j+2]+rc[i+3][j+3]+rc[i+4][j+4]))) == snuke:
            for k in reversed(range(5)):
                print(i+k+1, j+k+1)

# 右上斜め
for i in range(5, h):
    for j in range(w-4):
        if rc[i][j]+rc[i-1][j+1]+rc[i-2][j+2]+rc[i-3][j+3]+rc[i-4][j+4] == snuke:
            for k in range(5):
                print(i-k+1, j+k+1)
        if ''.join(list(reversed(rc[i][j]+rc[i-1][j+1]+rc[i-2][j+2]+rc[i-3][j+3]+rc[i-4][j+4]))) == snuke:
            for k in reversed(range(5)):
                print(i-k+1, j+k+1)