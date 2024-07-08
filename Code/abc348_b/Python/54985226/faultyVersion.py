import math

N=int(input())
poi=[list(map(int,input().split())) for _ in range(N)]

for x,y in poi:
    ans=0
    dist=0
    for i,(x_i,y_i) in enumerate(poi,1):
        if dist<math.sqrt((x-x_i)**2+(y-y_i)**2):
            dist=math.sqrt((x-x_i)**2+(y-y_i)**2)
            ans=i
        print(ans)