N, M = map(int, input().split())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

ans = "No"
C = sorted(A + B)
for i in range(len(C)-1):
  if (C[i] in A and C[i+1] in A) or (C[i] in B and C[i+1] in B):
    ans = "Yes"
print(ans)