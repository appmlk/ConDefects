import sys

def favorite_game(N, M, A):
    lower_start, upper_start = A[0], A[1]
    B = sorted(A[2:])

    min_move = float("inf")
    for left in range(len(B) - M + 1):
        right = left + M - 1
        lower = B[left]
        upper = B[right]
        move = 0
        if lower < lower_start:
            move += lower_start - lower
        if upper_start < upper:
            move += upper - upper_start
        min_move = min(min_move, move)

    return min_move

def resolve():
    N, M = [int(e) for e in sys.stdin.readline().split()]
    A = [int(e) for e in sys.stdin.readline().split()]
    print(favorite_game(N, M, A))

resolve()