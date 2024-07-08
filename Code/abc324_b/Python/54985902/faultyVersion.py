N = int(input())

while N % 2 == 0:
    N //= 2
print(N)
while N % 3 == 0:
    N //= 3
print(N)
print("Yes" if N == 1 else "No")