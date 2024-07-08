import sys

def main():
    input = sys.stdin.readline
    T = input().rstrip()
    N = int(input())
    l = len(T)
    dp = [[10**9] * (l + 1) for _ in [0] * (N + 1)]
    dp[0][0] = 0
    for i in range(N):
        a,*si = input().rstrip().split()
        for j in range(l):
            if dp[i][j] + 1:
                dp[i+1][j] = min(dp[i+1][j],dp[i][j])
                for s in si:
                    if T[j:j+len(s)] == s:
                        dp[i+1][j+len(s)] = min(dp[i+1][j+len(s)],dp[i][j] + 1)
    print(dp[N][l] if dp[N][l] < 10**9 else -1)

if __name__ == '__main__':
    main()