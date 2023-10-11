N = int(input())
A = list(map(int, input().split()))
mna1 = min(A[:N])

B = [(a, i) for i, a in enumerate(A[:N])]
B.sort()

x = 10**10
li = 0
for i in range(N):
    if A[i] == mna1:
        x = min(A[i+N], x)
        li = i
if x < mna1:
    print(mna1, x)
    exit()
ans1 = []
ans2 = []
f = True
f2 = False
for i in range(li+1):
    if A[i] == mna1:
        ans1.append(A[i])
        ans2.append(A[i+N])
        if f and len(ans2) >= 2:
            if ans2[-2] < ans2[-1]:
                f2 = True
                f = False
            if ans2[-2] > ans2[-1]:
                f = False

for b, i in B:
    if i <= li or b > ans2[0]:
        continue
    elif b == ans2[0]:
        if f2:
            ans1.append(b)
            ans2.append(A[i+N])
            li = i
    else:
        ans1.append(b)
        ans2.append(A[i+N])
        li = i

print(*ans1, *ans2)