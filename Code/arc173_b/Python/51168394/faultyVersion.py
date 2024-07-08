N=int(input())
P=[list(map(int,input().split())) for i in range(N)]

lin={}
for i in range(N-1):
    a,b=P[i]
    for j in range(i+1,N):
        c,d=P[j]
        lin=[b-d,c-a,a*b-b*c]
        count=0
        for k in range(N):
            x,y=P[k]
            if lin[0]*x+lin[1]*y+lin[2]==0:
                count+=1
        if count>N-N//3:
            print(N-count)
            exit()
print(N//3)


