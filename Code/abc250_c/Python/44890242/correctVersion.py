n, q = map(int, input().split())

idx = list(range(n+1))
num = list(range(n+1))

for i in range(q):
    x = int(input())

    y = idx[x]
    if y == n:
        y = n-1
    
    num[y], num[y+1] = num[y+1], num[y]
    idx[num[y]] = y
    idx[num[y+1]] = y+1
print(*num[1:])