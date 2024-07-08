from collections import defaultdict


def solution():
    N, M, K = map(int, input().split())
    c_lst = list(map(lambda s: int(s) - 1, input().split()))
    cnt = defaultdict(int)
    for c in c_lst:
        cnt[c] += 1
    c_lst *= 2

    res = 0  # number of total balls in boxes within range [i,i+N-1]
    box = 0  # number of boxes currently being used within range [i,i+N-1]
    dp = [0] * N  # dp[c] = number of color c balls in boxes within range [i,j]

    j = 0
    for i in range(N):
        if i > 0:
            # print("remove at i={}".format(i))
            dp[c_lst[i - 1]] -= 1
            if dp[c_lst[i - 1]] % K == 0:
                box -= 1
                res -= min(K, 1 + (cnt[c_lst[i - 1]] - dp[c_lst[i - 1]] - 1))
                # print("remove more at i={} for {}".format(i, min(K, 1+(cnt[c_lst[i-1]]-dp[c_lst[i-1]]-1))))
        while j < i + N and box < M:
            # print("scan i,j={}".format((i,j)))
            dp[c_lst[j]] += 1
            if dp[c_lst[j]] % K == 1:
                box += 1
                res += min(K, cnt[c_lst[j]] - dp[c_lst[j]] + 1)
                # print("include at i,j={} for {}".format((i,j), min(K, cnt[c_lst[j]]-dp[c_lst[j]]+1)))
            j += 1
        print(res)

    return


solution()