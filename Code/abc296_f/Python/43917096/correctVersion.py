n = int(input())
a = list(int(c)-1 for c in input().split())
b = list(int(c)-1 for c in input().split())

if sorted(a) != sorted(b):
    print('No')
    exit()
if len(set(a)) < n:
    print('Yes')
    exit()

b2i = [0]*n
for i,v in enumerate(b):
    b2i[v] = i
p = [0]*n
for i,j in enumerate(a):
    p[i] = b2i[j]
visited = [0]*n
cnt = 0
for v0 in range(n):
    if visited[v0]:
        continue
    v = v0
    while not visited[v]:
        visited[v] = 1
        v = p[v]
    cnt += 1
print('Yes' if (n-cnt)%2 == 0 else 'No')