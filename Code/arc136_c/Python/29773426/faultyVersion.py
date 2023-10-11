N = int(input())
A = list(map(int, input().split()))
B = []
before = A[-1]
for a in A:
    B.append(a - before)
    before = a

ans = sum([b for b in B if b > 0])
print(max(ans, min(A)))
