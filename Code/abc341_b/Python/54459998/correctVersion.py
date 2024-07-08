N = int(input())
A = list(map(int, input().split()))

ex = []

for _ in range(N-1):
    s,t = map(int, input().split())
    ex.append([s,t])

# print(ex)

# ex_sorted = sorted(ex, key=lambda x:x[1], reverse=True)
# ex_sorted2 = sorted(ex_sorted, key = lambda x: x[0])
# print(ex_sorted2)

for i in range(N-1):
    s = ex[i][0]
    t = ex[i][1]
    temp = A[i]//s
    A[i] -= temp * s
    A[i+1] += temp * t
    # print(A)
print(A[N-1])