def solve():
    N = int(input())
    S = input()
    for i in range(1, N):
        if S[0] < S[i]:
            print('Yes')
            return
    print('No')
    return

T = int(input())
for i in range(T):
    solve()