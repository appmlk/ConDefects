"""
Author ankisho
Created 2023/08/31 16:46JST
"""
from collections import deque

def operation(sh,sw,dh,dw,nope):
    nh = sh
    nw = sw
    while True:
        nh+=dh
        nw+=dw
        if nh<0 or nh>=N or nw<0 or nw>=N: break
        if S[nh][nw]=='#': break
        if dis[nh][nw]==-1:
            dis[nh][nw]=nope
            que.append((nh,nw,nope))
        elif dis[nh][nw]<nope:
            break

N = int(input())
A,B = map(int,input().split())
C,D = map(int,input().split())

S = [input() for _ in range(N)]

dis = [[-1]*N for _ in range(N)]

dis[A-1][B-1]=0

que = deque()
que.append((A-1,B-1,0))

while que:
    h,w,d = que.popleft()
    #各方向に移動できるまで移動させる
    operation(h,w,1,1,d+1)
    operation(h,w,1,-1,d+1)
    operation(h,w,-1,1,d+1)
    operation(h,w,-1,-1,d+1)

#print(dis)
print(dis[C-1][D-1])
