N = int(input())
S,T = [], []
for i in range(N):
    if i % 2 == 1:
        S.append([i*N+x+1 for x in range(N)])
    else:
        T.append([i*N+x+1 for x in range(N)])


for s, t in zip(S,T):
    print(*s)
    print(*t)