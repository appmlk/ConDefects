def main():
    T = input()
    N = int(input())
    S = []
    for _ in range(N):
        tmp = input().split()
        del(tmp[0])
        S.append(tmp) 


    INF = 10**18
    # dp[i] : i番目の袋から出した後
    # dp[i][j] : j文字目まで完成しているときの最小スコア
    dp = [([0] + [INF]*(len(T))) for _ in range(N+1)]
    
    for i in range(1, N+1):
        # 何も取り出さないとき
        dp[i] = dp[i-1]

        for s in S[i-1]:
            # j:足す前の文字数
            for j in range(len(T)+1):
                # 足したときの文字数
                post_len = j + len(s)
                if post_len > len(T):
                    break

                # 足した結果が正しいか
                if (T[0:j]+s == T[0:post_len]):
                    # 0から足したとき
                    if j == 0:
                        dp[i][post_len] = 1
                    else:
                        dp[i][post_len] = min(dp[i][post_len], dp[i-1][j] + 1)

    if dp[N][len(T)] > N:
        print(-1)
    else:
        print(dp[N][len(T)])

    return

main()