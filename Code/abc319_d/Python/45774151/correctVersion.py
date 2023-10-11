import itertools # permutation
import heapq # queue

if __name__ == '__main__':
    n, m = map(int, input().split())
    lis = list(map(int, input().split()))
    for i in range(n):
        lis[i] += 1
    
    # 二分探索
    l = max(lis)-1
    r = sum(lis)
    while l+1 < r:
        mid = (l+r)//2
        row = 1
        cnt = 0
        for i in range(n):
            if mid < cnt + lis[i]:
                row += 1
                cnt = lis[i]
            else:
                cnt += lis[i]
        if row <= m:
            r = mid
        else:
            l = mid
    print(r-1)