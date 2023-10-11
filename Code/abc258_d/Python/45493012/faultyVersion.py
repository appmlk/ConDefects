INT = lambda : int(input())
MI = lambda : map(int, input().split())
MI_DEC = lambda : map(lambda x : int(x)-1, input().split())
LI = lambda : list(map(int, input().split()))
LI_DEC = lambda : list(map(lambda x : int(x)-1, input().split()))

N, X = MI()
AB = [LI() for i in range(N)]

sum = 0
ans = float('inf')
for i in range(1, N+1):
    a, b = AB[i-1][0], AB[i-1][1]
    sum += (a + b)
    ans = min(ans, sum + (X - i)*b)

print(ans)