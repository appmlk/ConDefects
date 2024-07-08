## https://atcoder.jp/contests/abc307/tasks/abc307_g





def main():
    N = int(input())
    A = list(map(int, input().split()))

    sum_a = sum(A)

    if sum_a % N == 0:
        border_a = sum_a // N
        answer = 0
        for i in range(N - 1):
            b = border_a - A[i]
            A[i + 1] -= b
            answer += abs(b)
        print(answer)
        return
    else:
        mod = sum_a % N
        low_border_a = sum_a // N
        high_border_a = low_border_a + 1


        dp = [[None for _ in range(mod + 1)] for _ in range(N)]
        dp[0][0] = 0
        mins = [None for _ in range(mod + 1)]
        mins[0] = A[0]
        for i in range(N - 1):
            new_mins = [None for _ in range(mod + 1)]
            for m in range(mod + 1):
                if dp[i][m] is None:
                    continue

                # low_border_aに合わせる
                b = low_border_a - mins[m]
                if dp[i + 1][m] is None:
                    dp[i + 1][m] = float("inf")
                if dp[i + 1][m] > dp[i][m] + abs(b):
                    dp[i + 1][m] = min(dp[i + 1][m], dp[i][m] + abs(b))
                    new_mins[m] = A[i + 1] - b

                # high_border_aに合わせる
                if m + 1 <= mod:
                    if dp[i + 1][m + 1] is None:
                        dp[i + 1][m + 1] = float("inf")

                    b = high_border_a - mins[m]
                    if dp[i + 1][m + 1] > dp[i][m] + abs(b):
                        dp[i + 1][m + 1] = min(dp[i + 1][m + 1], dp[i][m] + abs(b))
                        new_mins[m + 1] = A[i + 1] - b
            mins = new_mins

        answer = dp[N - 1][mod]
        print(answer)







if __name__ == "__main__":
    main()
    