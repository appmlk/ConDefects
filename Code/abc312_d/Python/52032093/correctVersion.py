# D - Count Bracket Sequences
import math

def main():
  S = input()
  n = len(S)
  dp = [[0] * (n+1) for _ in range(n+1)]
  dp[0][0] = 1
  mod = 998244353

  for i in range(n):
    for j in range(i+1):
      half = math.ceil((i+1)/2)
    
      if S[i] == '(' or S[i] == '?':
        if j+1 >= half:
          dp[i+1][j+1] += dp[i][j]
          dp[i+1][j+1] %= mod
          
      if S[i] == ')' or S[i] == '?':
        if j >= half:
          dp[i+1][j] += dp[i][j]
          dp[i+1][j] %= mod
  
  if n % 2 == 0:
    print(dp[n][n//2])
  else:
    print(0)
  

if __name__ == '__main__':
  main()