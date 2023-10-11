n, a, b = map(int, input().split())


def f(x):
    return x // a * min(a, b) + min(x % a, b - 1)


print(max(f(n) - f(a - 1), 0))
