import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


def solve(L):
    if sum(L) % 3:
        return -1
    
    num = sum(L) // 3
    tmp1 = 0
    tmp2 = 0

    for x in L:
        if abs(num - x) % 2:
            return -1
        if x - num < 0:
            tmp1 += (num - x) // 2
        else:
            tmp2 += (x - num) // 2
    
    return tmp1

T = int(readline())
for _ in range(T):
    L = list(map(int, readline().split()))
    ans = solve(L)
    print(ans)
