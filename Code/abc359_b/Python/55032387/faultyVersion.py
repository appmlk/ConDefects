N = int(input())
A = input()
count = 0
for i in range(2*N-2):
  if A[i] == A[i+2]:
    count += 1
print(count)