import sys
sys.setrecursionlimit(1000000)

t = int(input())

def dfs(x):
    for y in edges[x]:
        dfs(y)
        sizes[x] += sizes[y]
        nums[x] += nums[y]
    sizes[x] += 1
    nums[x].append(A[x])


for _ in range(t):
    n, k = map(int, input().split())
    P = [-1]+list(map(lambda x: int(x)-1, input().split()))
    edges = [[] for _ in range(n)]
    for i in range(1, n):
        edges[P[i]].append(i)
    A = list(map(int, input().split()))

    sizes = [0]*n
    nums = [[] for _ in range(n)]
    dfs(0)

    for i in range(n):
        x = nums[i].count(-1)
        s = set(nums[i])
        if k in s:
            continue
        y = 0
        mex = k
        for j in range(k):
            if j not in s:
                y += 1
                if mex == k:
                    mex = j
            
        if (x==1 and y==1) or (mex==k and x==0):
            print("Alice")
            break
    else:
        print("Bob")