n = list(map(int, input().split()))
a = list(map(int, input().split()))
c = 0
b = 0
for i in range(n[0]):
    c = c + a[i]
    b = b + 1
    if c >= n[1]:
        break
if c == n[1]:
    print(b)
elif c < n[1]:
    print(1)
else:
    print(b - 1)
