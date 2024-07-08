N = int(input())

W,X = [],[]

for i in range(N) :
    w,x = map(int,input().split())
    W.append(w)
    X.append(x)

max_count = 0
for j in range(24):
    count = 0
    for i in range(N):
        now_time = (X[i]+j)%24
        if ((now_time >= 9)and(now_time <= 18)):
            count += W[i]
    max_count = max(max_count,count)
print(max_count)