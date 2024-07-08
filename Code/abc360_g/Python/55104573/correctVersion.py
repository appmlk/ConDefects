import bisect
from collections import deque
def solveLIS(lst):
    if lst == []:
        return 0
    LIS = [lst[0]]
    ex = []
    for i in range(len(lst)):
        if lst[i] > LIS[-1]:
            ex.append(len(LIS))
            LIS.append(lst[i])
            
        else:
            x = bisect.bisect_left(LIS,lst[i])
            LIS[x] = lst[i]
            ex.append(x)
    return LIS, ex


N = int(input())
A = list(map(int,input().split()))
LIS, ex = solveLIS(A)
LIS_size = len(LIS)


size = [deque() for _ in range(N)]
for i in range(N):
    size[ex[i]].append(i)

def can_extend(A,N,LIS,size):
    LIS_rev = [-A[-1]]
    for i in reversed(range(N)):
        if -A[i] > LIS_rev[-1]:
            x = len(LIS_rev)
            LIS_rev.append(-A[i])
        else:
            x = bisect.bisect_left(LIS_rev,-A[i])
            LIS_rev[x] = -A[i]

        while LIS_size - x > 1 and size[LIS_size - x - 2]:
            j = size[LIS_size - x - 2].pop()
            if i <= j+1:
                continue
            if A[j] < A[i]-1:
                return True
            else:
                size[LIS_size - x - 2].append(j)
                break

    if len(solveLIS(A[:-1] + [10**10])[0]) > len(LIS):
        return True
    if len(solveLIS([0] + A[1:])[0]) > len(LIS):
        return True
    return False

if can_extend(A,N,LIS,size):
    print(LIS_size+1)
else:
    print(LIS_size)
        
