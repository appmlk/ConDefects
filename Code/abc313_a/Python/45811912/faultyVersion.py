import sys
N, *P = map(int, sys.stdin.buffer.read().split())
if N > 1:
  print(max(0, max(P[1:])-P[0]+1))
else:
  print(P[0])