N = int(input())
A = list(map(int, input().split()))
ans = []
num = 0
i = 0
while i < N:
    if A[i] == i + 1:
        i += 1
        continue
    else:
        j = A[i] - 1
        num += 1
        ans.append([i + 1, j + 1])
        A[i], A[j] = A[j], A[i]

print(num)
for i in range(num):
    print(' '.join(map(str, ans[i])))
            