n, k = list(map(int, input().split()))
a = list(map(int, input().split()))
b = []
for i in range(n):
    if a[i] % k == 0:
        c = a[i] / k
        b.append(c)
sorted_b = sorted(b)
print(" ".join(map(str, sorted_b)))
