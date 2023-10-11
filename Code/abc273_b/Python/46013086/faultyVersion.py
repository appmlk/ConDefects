x, k = map(int,input().split())
p = 1

for i in range(k):
    x /= p
    m = x % 10
    if m <= 4:
        x -= m
    else:
        x += 10 - m

    x *= p
    p *= 10

print(x)
