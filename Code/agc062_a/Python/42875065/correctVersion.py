def solve(N, S):
    if S[-1] == 'A':
        return 'A'
    else:
        if S.count('A') == 0 or S.count('BA') == 0:
            return 'B'
        else:
            return 'A'

T = int(input())
for _ in range(T):
    N = int(input())
    S = input()
    print(solve(N, S))
