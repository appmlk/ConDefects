n = int(input())
A = list(map(int, input().split()))

ans = []

for i in range(n):
    tmp = A[i]
    ans.append(tmp)
    while True:
        if len(ans) == 1:
            break
        elif ans[-1] != ans[-2]:
            break
        bai = ans.pop() + 1
        ans.pop()
        ans.append(bai)
    # print(ans)
print(len(ans))