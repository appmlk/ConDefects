n = int(input())
a = sorted(list(map(int, input().split())))

avg = sum(a) // n
extra = sum(a) % n
ans = 0
avg_list = [avg if i<extra else avg+1 for i in range(n)]
for i in range(n):
    ans += abs(a[i] - avg_list[i])
print(ans // 2)
