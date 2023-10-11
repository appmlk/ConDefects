a = list(map(int, input().split()))
a.sort()
if a[2] > sum(a) * 2:
    print(-1)
elif a[0] == 0 and a[1] < a[2]:
    print(-1)
else:
    print(a[2])