h,w=map(int,input().split())
grid=[['.' for _ in range(w+2)]]
for _ in range(h):
    tmp=list(input())
    grid.append(['.']+tmp+['.'])
grid.append(['.' for _ in range(w+2)])

if grid[1][1]!='s':
    print('No')
else:
    bool=[[False for _ in range(w+2)] for __ in range(h+2)]
    bool[1][1]=True
    box=[(1,1)]
    direction=[(-1,0),(1,0),(0,-1),(0,1)]
    d=dict()
    tmp=['s','n','u','k','e','s']
    for i in range(5):
        d[tmp[i]]=tmp[i+1]
    
    while len(box)>0:
        H,W=box.pop()
        for dh, dw in direction:
            if bool[H+dh][W+dw]==False and grid[H+dh][W+dw]==d[grid[H][W]]:
                bool[H+dh][W+dw]=True
                box.append((H+dh,W+dw))

    if grid[h][w]:
        print('Yes')
    else:
        print('No')