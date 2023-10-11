import itertools


def main():
    n = int(input())
    xy = [list(map(int, input().split())) for _ in range(n)]
    ans = 0
    for a, b, c in itertools.combinations(xy, 3):
        s = abs((a[0] - c[0]) * (b[1] - c[1]) - (b[0] - c[0]) * (a[1] - c[1]))
        if s > 0:
            ans += 1
    print(ans)


if __name__ == '__main__':
    main()
