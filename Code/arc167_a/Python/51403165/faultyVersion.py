n, m = map(int, input().split())
a = list(map(int, input().split()))
a.sort(reverse=True)

single_sz = n - (n-m)*2
print(single_sz)

ans = 0
for i in range(single_sz):
    ans += a[i]**2

for i in range(n-m):
    i1 = single_sz+i
    i2 = n-1-i
    ans += (a[i1]+a[i2])**2

print(ans)
