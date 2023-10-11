# D - M<=ab
import math

def main():
  N, M = map(int, input().split())
  n = int(math.sqrt(M*10))
  ans = []

  for a in range(1, min(n, N)+1):
    b = ceil(M, a)

    if b <= N:
      ans.append(a*b)
  
  if len(ans) == 0:
    print(-1)
  else:
    print(min(ans))


def ceil(dividend, divisor):
  q = dividend // divisor

  if dividend % divisor > 0:
    q += 1

  return q


if __name__ == '__main__':
  main()