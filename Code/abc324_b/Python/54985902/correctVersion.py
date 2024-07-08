N = int(input())

while N % 2 == 0:
    N //= 2

while N % 3 == 0:
    N //= 3

print("Yes" if N == 1 else "No")