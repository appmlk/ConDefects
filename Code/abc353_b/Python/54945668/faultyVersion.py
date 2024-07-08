n, k = map(int, input().split())
a = list(map(int, input().split()))

seat_count = k
dep_count = 0
for i in range(n):
    if seat_count > a[i]:
        seat_count -= a[i]
    elif seat_count < a[i]:
        dep_count += 1
        seat_count = k
        seat_count -= a[i]
        if seat_count == 0:
            dep_count += 1
            seat_count = k
    elif seat_count == a[i]:
        dep_count += 1
        seat_count = k

if seat_count != 0:
    dep_count += 1

print(dep_count)