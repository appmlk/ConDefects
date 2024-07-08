N,A =map(int,input().split())
T = list(map(int,input().split()))
time = 0

for i in range(N):
  time = max(time, T[i]) + A
print(time, end = ' ')