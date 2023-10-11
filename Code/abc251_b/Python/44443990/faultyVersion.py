n, w = map(int, input().split())
a = list(map(int, input().split()))
sum_set = set(a)

#2個の和
for i in range(0, n-1):
    for j in range(i+1, n):
        s = a[i] + a[j]
        if s<=w:
            sum_set.add(s)

#3個の和
for i in range(0, n-2):
    for j in range(i+1, n-1):
        for k in range(j+1, n):
            s = a[i] + a[j] + a[k]
            if s<=w:
                sum_set.add(s)

cnt = 0
for i in range(w):
    if i in sum_set:
        cnt+=1

print(cnt)