N, M = map(int, input().split())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

#A,Bの判定
def check(x):
    #売ってもいい人数
    anum = 0
    for i in A:
        if i <= x:
            anum += 1
    #買ってもいい人数
    bnum = 0
    for i in B:
        if i >= x:
            bnum += 1
            
    return True if anum >= bnum else False

Le = 0
Ri = 1_000_000_001
#checkを満たすxを二分探索する
while abs(Le-Ri)>1:
    mid = (Le+Ri)//2
    flag = check(mid)
    if flag:
        Ri = mid
    else:
        Le = mid
print(Ri)
        