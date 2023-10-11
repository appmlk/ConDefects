from collections import deque

N = int(input())
A = [int(x) for x in input().split()]

A.sort()

deq = deque()
se = set()

non_need = deque()
for a in A:
    if a not in se:
        se.add(a)
        deq.append(a)
    else:
        non_need.append(a)

deq = deq + non_need

for i in range(N):
    ni = i + 1
    if deq and deq[0] == ni:
        deq.popleft()
    elif len(deq) >= 2:
        deq.pop()
        deq.pop()
    else:
        print(i)
        break
