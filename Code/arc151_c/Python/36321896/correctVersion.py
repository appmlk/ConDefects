n, m = map(int, input().split())
before_x = -1
before_y = -1
grundy = 0
for _ in range(m):
    x, y = map(int, input().split())
    if before_x == -1:
        grundy ^= x - 1
    else:
        grundy ^= (before_y ^ y + 1) % 2
    before_x, before_y = x, y

if before_x == -1:
    grundy ^= n % 2
else:
    grundy ^= n - before_x
if grundy:
    print("Takahashi")
else:
    print("Aoki")
