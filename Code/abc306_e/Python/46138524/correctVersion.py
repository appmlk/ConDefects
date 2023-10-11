from sortedcontainers import SortedList

N, K, Q = map(int, input().split())
XY = [tuple(map(int, input().split())) for _ in range(Q)]

A = [0] * N
lows = SortedList([0] * (N - K))
highs = SortedList([0] * K)
high_sum = 0

answers = []
for x, y in XY:
    x -= 1
    prev = A[x]
    A[x] = y
    if prev in lows:
        lows.discard(prev)
        lows.add(y)
    else:
        highs.discard(prev)
        highs.add(y)
        high_sum += y - prev
    if lows and highs:
        max_lows = lows[-1]
        min_highs = highs[0]
        if max_lows > min_highs:
            high_sum += max_lows - min_highs
            lows.discard(max_lows)
            lows.add(min_highs)
            highs.discard(min_highs)
            highs.add(max_lows)
    answers.append(high_sum)

print(*answers, sep="\n")
