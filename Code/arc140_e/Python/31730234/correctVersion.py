import sys
import math

N, M = map(int, input().split())
S = [[0]*500 for _ in range(500)]
base = list(range(23))
for i in range(500):
    for j in range(500):
        x = j//23
        S[i][j] = (i+j+(x*(i//23)))%23
for i in range(N):
    print(*map(lambda x: x+1, S[i][:M]))
