n, m, d = map(int, input().split())
arr = list(map(int, input().split()))
scores = list(map(int, input().split()))+[0]
if n == 1:
    print(scores[0])
    exit()
pos = list(range(-d*(n//2), d*((n+1)//2), d))
ans = 0
i = 1
for sc in range(0, d*((n+1)//2), d):
    while i <= m and arr[i] < sc:
        i += 1
    ans += scores[i-1]
i = 1
for sc in range(d, d*(n//2+1), d):
    while i <= m and arr[i] < sc:
        i += 1
    ans += scores[i-1]
xd = [0]*d
for i in range(1, m+1):
    x = arr[i]
    if x < d*((n+1)//2):
        xd[x%d] += scores[i]-scores[i-1]
for i in range(1, m+1):
    x = -arr[i]
    if x > -d*(n//2):
        xd[(x%d-1)%d] -= scores[i]-scores[i-1]
for i in range(1, d):
    xd[i] += xd[i-1]
print(ans+max(xd))
