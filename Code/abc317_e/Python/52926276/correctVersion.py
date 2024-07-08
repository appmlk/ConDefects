h,w=map(int, input().split())
g=[input() for i in range(h)]
used=[[1]*w for i in range(h)]

def grid(nowh,noww):
    if(0<=nowh<=h-1 and 0<=noww<=w-1):
        return 1
    else:
        return 0
st={"#", ">", "v", "<", "^"}
for i in range(h):
    for j in range(w):
        now=g[i][j]
        if(now==">"):
            used[i][j]=0
            dw=1
            while(1):
                if(grid(i,j+dw) and g[i][j+dw]=="."):
                    used[i][j+dw]=0
                    dw+=1
                else:
                    break
        elif(now=="<"):
            used[i][j]=0
            dw=-1
            while(1):
                if(grid(i,j+dw) and g[i][j+dw]=="."):
                    used[i][j+dw]=0
                    dw+=-1
                else:
                    break
        elif(now=="v"):
            used[i][j]=0
            dh=1
            while(1):
                if(grid(i+dh,j) and g[i+dh][j]=="."):
                    used[i+dh][j]=0
                    dh+=1
                else:
                    break
        elif(now=="^"):
            used[i][j]=0
            dh=-1
            while(1):
                if(grid(i+dh,j) and g[i+dh][j]=="."):
                    used[i+dh][j]=0
                    dh+=-1
                else:
                    break
        elif(now=="#"):
            used[i][j]=0
        
        elif(now=="S"):
            sh,sw=i,j
        elif(now=="G"):
            gh,gw=i,j

# for i in used:
#     print(*i)
from collections import deque, defaultdict
dir=[(1,0),(-1,0),(0,1),(0,-1)]
def bfs(sh,sw):
    dq=deque()
    dq.append((sh,sw))
    inf=float('inf')
    dist=[[inf]*w for i in range(h)]
    dist[sh][sw]=0
    used[sh][sw]=0
    while(dq):
        nowh,noww=dq.popleft()
        if (nowh,noww)==(gh,gw):
            break
        # print(nowh,noww)
        for dh,dw in dir:
            nexth,nextw=nowh+dh,noww+dw
            if(grid(nexth,nextw) and used[nexth][nextw]):
                dist[nexth][nextw]=dist[nowh][noww]+1
                dq.append((nexth,nextw))
                used[nexth][nextw]=0
                    
    return dist

dist=bfs(sh,sw)
inf=float('inf')
if(dist[gh][gw]==inf):
    print(-1)
else:
    print(dist[gh][gw])