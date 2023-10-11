a,n = map(int,input().split())
nums =[-1]*(10**6)
from collections import deque
q = deque()
q.append(a)
nums[a] = 1
while q:
    x = q.popleft()
    y = x*a
    if y<10**6 and nums[y] ==-1:
        nums[y] = nums[x]+1
        q.append(y)
    if x>9:
        X = str(x)
        Z = X[-1]+X[:-1]
        z = int(Z)
        if z<10**6 and nums[z] ==-1:
            nums[z] = nums[x]+1
            q.append(z)
print(nums[n])

