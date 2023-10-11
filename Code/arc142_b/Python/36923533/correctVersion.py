n = int(input())
ans = []
import collections
suu = collections.deque(range(1,n+1))
for i in range(n):
    if i % 2 == 0:
        ans.append(suu.popleft())
    else:
        ans.append(suu.pop())
#print(ans)
for i in range(n):
    print(*ans)
    for j in range(n):
        ans[j] += n