def func(a,b):
    return pow(a,3) + pow(a,2) * b + a * pow(b,2) + pow(b,3)

n = int(input())
if n == 0:
    print(0)
    exit()

ans = float("inf")

a = 0
b = int(pow(n, 1/3)) + 1
x = func(a,b)
while a <= b:
    tempx = func(a,b)
    if tempx >= n:
        b -= 1
        x = tempx
        if ans > x:
            ans = x
    else:
        if ans > x:
            ans = x
        a += 1


print(ans)


