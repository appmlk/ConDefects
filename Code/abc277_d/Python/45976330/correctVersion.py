n, m = map(int,input().split())
A = list(map(int,input().split()))
sm = sum(A)
A.sort()
A = A + A

i = 0
ans = 1 << 61
while i < len(A) - 1:
    now = A[i]
    while A[i+1] == A[i] or A[i+1] == (A[i] + 1)%m:
        i += 1
        if i >= len(A) - 1:
            break
        now += A[i]

    ans = min(ans, sm - now)
    i += 1

if ans < 0:
    ans = 0

print(ans)

