import sys
import numpy as np


readline = sys.stdin.readline

N = int(input())
xy = np.array([list(map(int, readline().split())) for _ in range(N)])
dxdy = np.diff(xy, axis=0, append=[xy[0]])
coef = np.array([dxdy[:, 1], -dxdy[:, 0]]).T
cons = np.sum(coef * xy, axis=1, keepdims=True)

M = int(input())
uv = np.array([list(map(int, readline().split())) for _ in range(M)])
dist = np.min(coef @ uv.T + cons, axis=1)

Q = int(input())
ab = np.array([list(map(int, readline().split())) for _ in range(Q)])
ans = np.all(ab @ coef.T <= dist, axis=1)

print(*np.where(ans, "Yes", "No"), sep='\n')