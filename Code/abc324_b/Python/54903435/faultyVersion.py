n = int(input())

while n % 3 == 0:
    n //= 3

while n % 2 == 0:
    n //= 2

print("Yes" if n == 0 else "No")
