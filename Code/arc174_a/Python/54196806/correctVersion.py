N, C = map(int, input().split())
A = list(map(int, input().split()))
ruiseki_sei = [0]*(N+1)
ruiseki_hu = [0]*(N+1)

sum = 0
for i in range(N):
  sum += A[i]
  
for i in range(1,N+1):
  ruiseki_sei[i] = ruiseki_sei[i-1] + A[i-1]
  if ruiseki_sei[i] <= 0:
    ruiseki_sei[i] = 0
    
tmp = max(ruiseki_sei)

for i in range(1,N+1):
  ruiseki_hu[i] = ruiseki_hu[i-1] + A[i-1]
  if ruiseki_hu[i] >= 0:
    ruiseki_hu[i] = 0

tmp2 = min(ruiseki_hu)
if C > 0:
  print(sum + tmp*(C-1))
else:
  print(sum + -tmp2 + tmp2*C)