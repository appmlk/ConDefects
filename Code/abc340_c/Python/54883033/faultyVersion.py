import math
import functools


n = int(input())

@functools.cache
def calc(m):
  if m < 2:
    return 0
  return m + calc(int(m / 2)) + calc(int((m + 1) / 2))

print(calc(n))