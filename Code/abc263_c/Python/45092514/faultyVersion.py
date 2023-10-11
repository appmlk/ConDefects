N,M=map(int,input().split())

import itertools

for p in itertools.combinations(range(1,N+1),M):
  print(*p)