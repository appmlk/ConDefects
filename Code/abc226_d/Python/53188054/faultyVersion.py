from math import gcd


def distance(x1, y1, x2, y2, m):
    xa = x1 - x2
    ya = y1 - y2
    xb = x2 - x1
    yb = y2 - y1
    if xa == 0:
        ya //= abs(ya)
        yb //= abs(yb)
    elif ya == 0:
        xa //= abs(xa)
        xb //= abs(xb)
    else:
        g = gcd(xa, xb)
        xa //= g
        ya //= g
        xb //= g
        yb //= g
    m.add((xa, ya))
    m.add((xb, yb))


def main():
    n = int(input())
    m = set()
    p = []
    for _ in range(n):
        x, y = map(int, input().split())
        p.append((x, y))
    for i in range(n):
        for j in range(i + 1, n):
            distance(p[i][0], p[i][1], p[j][0], p[j][1], m)
    print(len(m))


if __name__ == '__main__':
    main()
