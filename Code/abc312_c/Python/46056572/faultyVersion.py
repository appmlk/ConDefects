N, M = map(int, input().split())
A = sorted(list(map(int, input().split())))
B = sorted(list(map(int, input().split())), reverse=True)

def bisect(ok, ng, solve):
    while abs(ok - ng) > 1:
        mid = (ng + ok) // 2
        if solve(mid):
            ok = mid
        else:
            ng = mid
    return ok

def solve(x):
    A_num = bisect(0, N, lambda m: A[m] <= x) + 1
    if A[A_num-1] > x:
        A_num = 0
    B_num = bisect(0, M, lambda m: B[m] >= x) + 1
    if B[B_num-1] < x:
        B_num = 0
    return A_num >= B_num

print(bisect(10 ** 9, -1, solve))
