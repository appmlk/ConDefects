N, X = map(int, input().split())
games = []

total = 0
min_clear = 10**20
for i in range(min(N, X)):
    a, b = map(int, input().split())
    total += (a + b)
    iteration = (X - i - 1) * b
    tmp_ans = total + iteration
    min_clear = min(min_clear, tmp_ans)

print(min_clear)
