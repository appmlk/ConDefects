def solve(N, S):
    if S[-1] == 'A':
        return 'A'
    else:
        if S.count('AB') == 1:
            return 'B'
        else:
            return 'A'

T = int(input())
for _ in range(T):
    N = int(input())
    S = input()
    print(solve(N, S))
