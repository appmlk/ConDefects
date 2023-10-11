from sys import stdin


def check(b, n, m):
    for i in range(n):
        if (b[i][0] - 1) % 7 + m > 7:
            return False
        if i + 1 < n and b[i + 1][0] != b[i][0] + 7:
            return False
        for j in range(m - 1):
            if b[i][j + 1] != b[i][j] + 1:
                return False
    return True


def main():
    input = stdin.readline
    n, m = map(int, input().split())
    b = [list(map(int, input().split())) for _ in range(n)]
    print("Yes" if check(b, n, m) else "No")


if __name__ == "__main__":
    main()
