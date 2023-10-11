N, M = map(int, input().split())
I = [list(map(int, input().split())) for _ in range(N)]

for i in range(N):
    for j in range(N):
        if i == j:
            continue
        if I[i][0] >= I[j][0]:
            fj = set(I[j][2:])
            fi = set(I[i][2:])
            if len((fj | fi)) == len(fj):
                if I[i][0] > I[j][0] or len((fj | fi)) - len(fi) > 0:
                    print('Yes')
                    exit()
print('No')