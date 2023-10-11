# 278a
n, k = map(int, input().split())
li = list(map(int, input().split()))

for i in range(k):
    li.pop(0)
    li.append(0)
print(*li)    