import numpy as np
import bisect
w, h = map(int,input().split())
n = int(input())
ichigo = [ list(map(int,input().split())) for _ in range(n)]
A = int(input())
a = list(map(int,input().split()))
B = int(input())
b = list(map(int,input().split()))

ans = {}
for i in range(n):
  s = bisect.bisect(a,ichigo[i][0])
  t = bisect.bisect(b,ichigo[i][1])
  if (s,t) in ans:
    ans[(s,t)] += 1
  else:
    ans[(s,t)] = 1



lis = list(ans.values())

M = max(lis)
if len(ans) == (A+1)*(B+1):
  n = min(lis)
else:
  n = 0
print(M,n)