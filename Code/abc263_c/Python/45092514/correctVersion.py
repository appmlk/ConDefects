N,M=map(int,input().split())

import itertools

for p in itertools.combinations(range(1,M+1),N):
  print(*p)