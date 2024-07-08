from collections import deque

N, M = map(int, input().split())
A = deque(map(int, input().split()))
C = deque(map(int, input().split()))

answer = []

while A[0] == 0:
    A.popleft()

A = list(A)

# 多項式を筆算で割り算する動きをシミュレーションする
while len(C) >= len(A):
    c = C.popleft()
    answer.append(c // A[0])

    for i, a in enumerate(A[1:]):
        C[i] = C[i] - a * (c // A[0])

# 割り切れること
assert sum(C) == 0

print(' '.join(map(str, answer[-(M + 1):])))
