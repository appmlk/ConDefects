N, M = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(M)]
if M == 0:
    print('Takahashi' if N % 2 else 'Aoki')
    exit()

state = 0
for i in range(M-1):
    a, b = A[i]
    c, d = A[i+1]
    state ^= b ^ d ^ 1

state ^= A[0][0]-1
state ^= N-A[-1][0]

print('Takahashi' if state else 'Aoki')
