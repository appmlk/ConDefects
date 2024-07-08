n = int(input())
alist = list(map(int, input().split()))
acc = 0
imin = -1
amin = 0
for i in range(n):
    acc += alist[i]
    if acc <= amin:
        amin = acc
        imin = i
print(sum(alist[imin+1:]))