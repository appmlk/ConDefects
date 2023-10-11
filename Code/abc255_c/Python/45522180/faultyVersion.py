INT = lambda : int(input())
MI = lambda : map(int, input().split())
MI_DEC = lambda : map(lambda x : int(x)-1, input().split())
LI = lambda : list(map(int, input().split()))
LI_DEC = lambda : list(map(lambda x : int(x)-1, input().split()))

X, A, D, N = MI()

def a(n):
    return A + (n - 1) * D

if D == 0:
    print(abs(A - X))
    exit(0)

i = max(1, (X - A + D) // D)

ans = abs(a(i) - X)
if i + 1 <= N:
    ans = min(ans, abs(a(i+1) - X))

print(ans)