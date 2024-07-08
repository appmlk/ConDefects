from functools import lru_cache
S_x = lambda : input()
N_x = lambda : int(input())
N_more = lambda : map(int, input().split())
A_L = lambda : list(map(int, input().split()))

N = N_x()

@lru_cache
def n_split(x):
  if x == 1:
    return 1
  else:
    if x % 2 == 0:
      return n_split(x//2) + n_split(x//2) + x
    else:
      return n_split(x//2) + n_split(x//2+1) + x

ans = n_split(N)-N
print(ans)
