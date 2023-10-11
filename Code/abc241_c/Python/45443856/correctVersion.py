N = int(input())
S = [input() for _ in range(N)]

dx = [1,0,1,-1]
dy = [0,1,1,1]

for h in range(N):
    for w in range(N):
        for i in range(4):
            cnt = 0
            for j in range(6):
                nh = h + dy[i]*j
                nw = w + dx[i]*j
                if not (0 <= nh < N and 0 <= nw < N):
                    break
                if S[nh][nw] == "#":
                    cnt += 1
            else:
                if cnt >= 4:
                    print('Yes')
                    exit()
print('No')


