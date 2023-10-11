x,k = list(map(int,input().split(" ")))
for i in range(k):
    x = round(x+0.0001,-(i+1))
print(int(x))