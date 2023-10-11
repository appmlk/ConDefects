n,m=map(int,input().split())
if not m:
    if n&1:
        print('Takahashi')
    else:
        print('Aoki')
    exit()
xy = [tuple(map(int,input().split())) for _ in range(m)]

g = (xy[0][0]-1)^(n-xy[-1][0])

for i in range(m-1):
    x1,y1 = xy[i]
    x2,y2 = xy[i+1]

    d = x2-x1-1
    if d==0:
        continue
    g^=y1^y2^1

if g:
    print('Takahashi')
else:
    print('Aoki')


