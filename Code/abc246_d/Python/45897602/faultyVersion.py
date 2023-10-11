n = int(input())

def f(a, b):
    return a **3 + a**2*b + a*b**2 + b**3

def binary_search(a):
    left = 1
    right = 10 ** 6+1
    while left < right:
        mid = (left + right) // 2
        if f(a, mid) >= n:
            right = mid
        else:
            left = mid + 1
    return left            

ans = 10**18+1

for a in range(10**6+1):
    b = binary_search(a)
    v = f(a, b)
    if v < ans:
        ans = v
print(ans)