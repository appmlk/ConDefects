import sys
def input(): return sys.stdin.readline().rstrip()
def read(): return int(input())
def reads(): return list(map(int, input().split()))

def main():
    MOD = 998244353
    
    N = read()
    AB = [reads() for _ in range(N)]
    
    dp = [[0]*2 for _ in range(N)]
    dp[0][0] = 1
    dp[0][1] = 1
    
    for i in range(1, N):
        for j in range(2):
            for k in range(2):
                    
                if AB[i][j] != AB[i-1][k]:
                    dp[i][j] += dp[i-1][k]
                    dp[i][j] %= MOD
    print(sum(dp[N-1])%MOD)

 
if __name__ == '__main__':
    main()