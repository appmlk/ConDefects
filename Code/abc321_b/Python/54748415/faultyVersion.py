n,x = map(int, input().split())
alist = list(map(int, input().split()))
s = sum(alist)
mx = max(alist)
mn = min(alist)
for i in range(100):
    if s + i - min(mn, i) - max(mx, i) >= x:
        print(i)
        exit()
print(-1)