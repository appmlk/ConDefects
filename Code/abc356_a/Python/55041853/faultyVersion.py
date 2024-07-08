n, l , r = map(int, input().split())
result = []
for i in range(1, l):
    result.append(i)
for i in range(r, l - 1, -1):
    result.append(i)
for i in range(r + 1, n + 1):
    result.append(i)
print(result)