n, a, b = map(int, input().split())
if a <= b:
    print(n)
else:
    if n < a:
        print(0)
    else:
        print((n // a - 1) * b + min(b - 1, n % a) + 1)
