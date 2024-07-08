n = int(input())
A = list(map(int, input().split()))
B = [0]*n
B[0] = A[0]
mi = 0
for i in range(1,n):
  B[i] = B[i-1] + A[i]
  if B[i] < B[mi]:
    mi = i
# print(B, mi)
ans = sum(A[mi+1:])
if 0 < B[mi]:
  ans += B[mi]
print(ans)