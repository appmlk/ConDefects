import sys

sys.setrecursionlimit(10**9)


def main():
    N, K = map(int, input().split())
    S = input()

    for i in range(1, N + 1):
        if N % i:
            continue

        D = [[0 for _ in range(26)] for _ in range(i)]
        T = [0 for _ in range(i)]

        for j, s in enumerate(S):
            D[j % i][ord(s) - ord("a")] += 1
            T[j % i] += 1

        k = 0
        for l, d in enumerate(D):
            d.sort(reverse=True)
            k += T[l] - d[0]

        if k <= K:
            print(i)
            return


if __name__ == "__main__":
    main()
