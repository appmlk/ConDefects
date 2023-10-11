N = int(input())
P = list(map(int, input().split()))

if P[0] == 1 and P[-1] == N:
    print(0)
    exit()

for i, (l, r) in enumerate(zip(P, P[1:])):
    if abs(l - r) == N - 1:
        break

ans = 0
if l < r:
    ans = min(i + 1 + 1, 1 + N - i - 1)
else:
    ans = min(i + 1, 2 + N - i - 1)

print(ans)
