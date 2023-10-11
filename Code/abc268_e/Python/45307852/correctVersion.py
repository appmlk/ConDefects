from collections import defaultdict
N = int(input())
p = list(map(int,input().split()))

ans = [0] * N
for i in range(N):
    ans[0] += min((p[i]-i)%N, (i-p[i])%N)

diff = 0
change = defaultdict(int)

if N & 1:
    for i in range(N):
        x = (p[i]-i) % N
        change[(N//2 - x) % N] -= 1
        change[(N//2 - x + 1) % N] -= 1
        change[(N - x) % N] += 2
        if x < N//2:
            diff += 1
        elif x > N//2:
            diff -= 1
            
else:
    for i in range(N):
        x = (p[i]-i) % N
        change[(N//2 - x) % N] -= 2
        change[(N - x) % N] += 2
        if x < N//2:
            diff += 1           
        else:
            diff -= 1

for i in range(N-1):
    ans[i+1] = ans[i] + diff
    diff += change[i+1]


print(min(ans))