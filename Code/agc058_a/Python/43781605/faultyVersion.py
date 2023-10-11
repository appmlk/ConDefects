N = int(input())
p = list(map(int, input().split()))
ans = []
for i in range(1, 2 * N - 1, 2):
    a, b, c = p[i-1], p[i], p[i+1]
    if (max(a, b, c) == a):
        ans.append(i)
        p[i-1], p[i] = p[i], p[i-1]
    elif (max(a, b, c) == c):
        ans.append(i+2)
        p[i], p[i+1] = p[i+1], p[i]

if p[2 * N - 2] > p[2 * N - 1]:
    ans.append(2 * N - 1)

print(len(ans))
print(*ans)