import math

a, b = map(int, input().split())
if a < b:
    a, b = b, a
ans = 0
while b > 0:
    g = math.gcd(a, b)
    a //= g
    b //= g
    diff = a - b
    if diff == 1:
        ans += b
        break
    else:
        m = float("inf")
        for i in range(1, int(diff**0.5) + 1):
            if diff % i == 0:
                if i != 1:
                    m = min(m, b % i)
                m = min(m, b % (diff // i))
        a -= m
        b -= m
        ans += m
print(ans)
