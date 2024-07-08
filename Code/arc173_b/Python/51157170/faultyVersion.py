
n = int(input())

import sys
input = sys.stdin.readline
mp = map(int, sys.stdin.read().split())
xy = list(zip(mp,mp)) 

stack = []
cnt = 0
ans = 0

for i in range(n):
    if cnt < 2:
        stack.append(i)
        cnt += 1
        continue

    xi,yi = xy[i]
    flag = False

    for ji in range(cnt):
        if flag:
            break
        j = stack[ji]
        
        xj,yj = xy[j]

        dx = xi-xj
        dy = yi-yj
        # if dx == 0:
        #     # dx = 0
        #     dy = 0
        # else:
        #     # if dx < 0:
        #     #     dx = -dx
        #     #     dy = -dy
        #     pass

        for ki in range(ji+1, cnt):
            if flag:
                break
            k = stack[ki]

            xk,yk = xy[k]
            ex = xi-xk
            ey = yi-yk

            # if ex == 0:
            #     # ex = 0
            #     ey = 0
            # else:
            #     # if ex < 0:
            #     #     ex = -ex
            #     #     ey = -ey
            #     pass
            
            # if (dx == ex) & (dy == ey):
            #     print("a", i,j,k)
            #     continue
            # elif dx == 0:
            #     print("b", i,j,k)
            #     pass
            # elif ex == 0:
            #     print("c", i,j,k)
            #     pass
            # else:
            if (dx == 0) | (ex == 0):
                if dx == ex:
                    continue
                else:
                    pass

            elif (dx * ey) == (dy * ex):
                # print("d", i,j,k)
                continue
            else:
                # print("e", i,j,k)
                pass
            
            stack.remove(k)
            stack.remove(j)
            cnt -= 2
            ans += 1
            flag = True
    
    if not flag:
        stack.append(i)
        cnt += 1

if cnt < 3:
    print(ans)
    exit()

j = stack[0]
k = stack[1]

# xj,yj = xy[j]
# xk,yk = xy[k]

def line(p,q):
    """
    2点p(px,py),q(qx,qy)を通る直線ax+by+c=0のa,b,cを返す
    """
    px,py = p
    qx,qy = q

    a = py - qy
    b = qx - px
    c = px*qy - py-qx

    return a,b,c

a,b,c = line(xy[j], xy[k])

cnt = 0
for i in range(n):
    xi,yi = xy[i]
    if (a*xi + b*yi + c) == 0:
        cnt += 1
    else:
        pass


if cnt >= (n-n//3):
    print(n-cnt)
else:
    print(n//3)
# print(ans)
