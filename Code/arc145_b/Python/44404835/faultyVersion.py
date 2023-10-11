n, a, b = map(int, input().split())


def f(N):
    cnt = N // a * min(a, b) + min(N % a, b - 1)
    return cnt


print(f(n) - f(a - 1))
