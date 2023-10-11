def update(index, val):
    # val 是增量, 如果是修改值记得计算增量
    # val = val - query_lr(index,index)
    while index <= n:
        tree[index] += val
        index += index & (-index)

def query(r):
    res = 0
    while r > 0:
        res += tree[r]
        r -= r & (-r)
    return res

from collections import Counter
n = int(input())
a = list(map(int,input().split()))
b = list(map(int,input().split()))
c = Counter(a)
if c != Counter(b):
    print('No')
    exit()
if any(i > 1 for i in c.values()):
    print('Yes')
    exit()
tree = [0]*5001
res = 0
for i in range(n):
    res += i - query(a[i])
    update(a[i],1)
tree = [0]*5001
for i in range(n):
    res += i - query(b[i])
    update(b[i],1)
if res%2 == 0:
    print('Yes')
else:
    print('No')