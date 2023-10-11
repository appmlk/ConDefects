import sys

sys.setrecursionlimit(10**9)


def main():
    N, K = map(int, input().split())
    A = list(map(int, input().split()))

    AA = [(a, i) for i, a in enumerate(A)]

    A1, A2 = AA[:K], AA[K:]
    A1.sort()
    A2.sort()
    D = [N for _ in range(len(A2) + 1)]
    for i, (a, j) in enumerate(reversed(A2)):
        D[len(A2) - i - 1] = min(D[len(A2) - i], j)

    ans = float("inf")
    for a, i in A1:
        l, r = -1, len(A2)
        while r - l > 1:
            m = (l + r) // 2
            if A2[m][0] > a:
                r = m
            else:
                l = m

        if r == len(A2):
            continue

        ans_t = (K - i - 1) + (D[r] - K)
        ans = min(ans, ans_t + 1)

    print(ans if ans != float("inf") else -1)


if __name__ == "__main__":
    main()
