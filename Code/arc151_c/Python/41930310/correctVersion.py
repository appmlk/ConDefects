import sys
read = sys.stdin.buffer.read

N,M,*XY = map(int,read().split())
if M==0 :
    if N % 2 == 1:
        g = 1
    else:
        g = 0
else:
    it = iter(XY)
    XY = [[xi,yi] for xi,yi in zip(it,it)]

    g = 0
    if XY[0][0] != 1:
        g ^= XY[0][0] - 1
    if XY[-1][0] != N:
        g ^= N - XY[-1][0]
    
    for i in range(M-1):
        xi,yi = XY[i]
        xj,yj = XY[i+1]
        if yi == yj and xj-xi > 1:
            g ^= 1

if g == 0:
    print('Aoki')
else:
    print('Takahashi')