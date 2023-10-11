import collections
N=int(input())
Ax,Ay=map(int,input().split())
Bx,By=map(int,input().split())
S=[input() for i in range(N)]

Ax-=1
Ay-=1
By-=1
Bx-=1

D=[[-1 for j in range(N)] for i in range(N)]
D[Ax][Ay]=0

Q=collections.deque()
Q.append((Ax,Ay))

while Q:
    h,w=Q.popleft()
    
    for dx in [-1, 1]:
        for dy in [-1, 1]:
            nh=h+dx
            nw=w+dy
            while True:
                if nh == -1 or nh == N or nw == -1 or nw == N:
                    break
                if S[nh][nw] == "#":
                    break
                if D[nh][nw] != -1 and D[nh][nw] <= D[nh][nw]:
                    break
                if D[nh][nw] < D[h][w]:
                    D[nh][nw] = D[h][w] + 1
                    Q.append((nh, nw))
                nh+=dx
                nw+=dy


print(D[Bx][By])