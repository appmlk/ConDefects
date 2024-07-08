import os, sys; sys.setrecursionlimit(10**7)
readline = sys.stdin.readline
if os.path.basename(__file__) == "Main.py":
  import pypyjit; pypyjit.set_param('max_unroll_recursion=-1')
from functools import cmp_to_key

alphabet_to_int = lambda x:ord(x)-97
mod = 998244353

def f(S):
  res = 0
  for i in range(len(S)):
    res += alphabet_to_int(S[~i]) * pow(26, i)
  return res

def compare(x, y):
  n = len(x)
  m = len(y)
  return f(x) * (pow(26, m) - 1) - f(y) * (pow(26, n) - 1)

def main():
  n, k = map(int, readline().split())
  S = [input() for _ in range(n)]
  S.sort(key=cmp_to_key(compare))
  dp = [[None] * (k + 1) for _ in range(n + 1)]
  for i in range(n + 1):
    dp[i][0] = ""
  for i in range(1, n + 1)[::-1]:
    for j in range(k + 1):
      if not dp[i][j] == None:
        if not dp[i - 1][j] == None:
          dp[i - 1][j] = min(dp[i][j], dp[i - 1][j])
        else:
          dp[i - 1][j] = dp[i][j]
        if j < k:
          if not dp[i - 1][j + 1] == None:
            dp[i - 1][j + 1] = min(dp[i - 1][j + 1], S[i - 1] + dp[i][j])
          else:
            dp[i - 1][j + 1] = S[i - 1] + dp[i][j]
  print(dp[0][-1])

if __name__ == '__main__':
  main()