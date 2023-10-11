n=int(input())
A=list(map(int,input().split()))
ans = [0]
for i in range(n):
    for j in range(len(ans)):
        ans[j] += A[i]
        ans[j] %= 360
    ans.append(0)
ans = sorted(ans)
ans.append(360)
Ma = 0
for i in range(n+1):
    Ma = max(Ma, ans[i+1]-ans[i])
print(Ma)
