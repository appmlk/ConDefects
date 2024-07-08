n,l,r = list(map(int,input().split()))
ans = [i + 1 for i in range(n)]
start = 1
for x in range(n):
    start += n - 1 - x
    if start > l:
        break
if start > r:
    for j in range(l, r + 1):
        ans[x], ans[j + n - start] = ans[j + n - start], ans[x]
    print(ans)
    exit()

for j in range(l,start):
    ans[x],ans[j + n - start] = ans[j + n - start],ans[x]
x += 1

end = n*(n - 1)//2
for y in range(n - 1,-1,-1):
    end -= n - 1 - y
    if end < r:
        break
if x < y:
    ans = ans[:x] + ans[x - y:][::-1] + ans[x:x - y]

for j in range(end + 1,r + 1):
    ans[y],ans[j + y - end] = ans[j + y - end],ans[y]
print(*ans)