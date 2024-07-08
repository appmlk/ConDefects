n, t = map(int, input().split())
a = list(map(int, input().split()))

b = [0] * (n * 2 + 2)

for i, ai in enumerate(a):
    r = (ai - 1) % n
    c = (ai - 1) // n
    b[r] += 1
    b[n + c] += 1

    if r == c:
        b[-2] += 1

    if r + c == n:
        b[-1] += 1

    if max(b) == n:
        print(i + 1)
        exit()

print(-1)
