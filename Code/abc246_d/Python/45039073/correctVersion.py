n = int(input())

def f(a, b):
    return a*a*a + a*a*b + a*b*b + b*b*b

ni = 10**6
ans = float('inf')
for a in range(ni+1):
    l = -1
    r = 10**6+1
    while r - l > 1:
        b = (r+l) // 2
        if f(a, b) < n:
            l = b
        else:
            r = b
    ans = min(ans, f(a, r))
print(ans)