n, x = map(int, input().split())

s = []
bs = []
for i in range(n):
    a, b = map(int, input().split())

    if i == 0:
        s.append(a+b)
    else:
        s.append(s[i-1] + a + b)

    bs.append(b)

ans = 100000000000000000
for max_stage in range(n):

    if x-1 <= max_stage:
        time = s[x-1]

    else:
        time = s[max_stage] + bs[max_stage] * (x-max_stage-1)

    ans = min(ans, time)

print(ans)
