N = int(input())
C = []
A = []
for _ in range(N):
    c = int(input())
    a = list(map(int, input().split()))
    C.append(c)
    A.append(a)
X = int(input())

p = []
for i in range(N):
    if X in A[i]:
        p.append(C[i])

if len(p) == 0:
    print(0)
    exit()
else:
    min = min(p)

ans = []
for j in range(N):
    if C[j] == min:
        ans.append(str(j + 1))

print(len(ans))
print(' '.join(ans))