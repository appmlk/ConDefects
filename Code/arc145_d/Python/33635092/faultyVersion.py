N, M = map(int, input().split())

ans = []
SUM = 0
count = 0

for i in range(10**7)[::-1]:
    OK = True
    cur = i
    while cur > 0:
        if cur % 3 == 2:
            OK = False
            break
        else:
            cur //= 3
    if OK:
        ans.append(i)
        SUM += i
        count += 1
    if count == N-2:
        break

to_increase = (M-SUM)%N

cur = to_increase
append1 = 0
append2 = 0

while cur > 0:
    i = 0
    if cur % 3 >= 1:
        append1 += 3**i
    if cur % 3 >= 2:
        append2 += 3**i
    cur //= 3
    i += 1

while append1 == append2:
    to_increase += N
    cur = to_increase
    append1 = 0
    append2 = 0

    i = 0
    while cur > 0:
        if cur % 3 >= 1:
            append1 += 3**i
        if cur % 3 >= 2:
            append2 += 3**i
        cur //= 3
        i += 1

SUM += to_increase
diff = (M-SUM)//N

ans.append(append1)
ans.append(append2)

for i in range(N):
    ans[i] += diff

print(*ans)