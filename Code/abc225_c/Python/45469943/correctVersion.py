
import sys

sys.setrecursionlimit(10**7)


def input():
    return sys.stdin.readline().rstrip()


def main():
    N, M = map(int, input().split())
    B = [list(map(int, input().split())) for _ in range(N)]

    for i in range(N):
        for j in range(M):
            if B[i][j] % 7 == 0 and j != M - 1:
                print("No")
                return

    T = [[0] * M for _ in range(N)]
    start = B[0][0]
    for i in range(N):
        for j in range(M):
            if i == 0 and j == 0:
                T[0][0] = start
            else:
                T[i][j] = start + (i * 7) + j

    if T == B:
        print("Yes")
    else:
        print("No")


if __name__ == "__main__":
    main()
