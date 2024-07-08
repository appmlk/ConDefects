A = list(map(int, input().split()))
N = int(input())
X = list(map(int, input().split()))


prices = [1, 5, 10, 50, 100, 500]


valid = True
for i in range(1, 6):
    n = 0
    for x in X:
        n += x % prices[i]
    if n > A[i - 1]:
        valid = False
        break
    A[i] += (A[i - 1] - n) // prices[i]
    X = [x // prices[i] for x in X]
    prices = [p // prices[i] for p in prices]

if valid and sum(prices) <= A[-1]:
    print("Yes")
else:
    print("No")
