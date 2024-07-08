n, x = map(int, input().split())
A = list(map(int, input().split()))

A.sort()

ans = float("inf")
for i in range(101):
  A.append(i)
  A.sort()
  a = sum(A[1:-1])
  if x <= a:
    ans = i
    break
  A.remove(i)
print(ans if ans!=float("inf") else -1)