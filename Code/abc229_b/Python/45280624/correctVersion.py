a, b = map(int, input().split())

while a > 0 and b > 0:
    if (a % 10) + (b % 10) >= 10:
        print("Hard")
        exit()
    a //= 10
    b //= 10

print("Easy")
