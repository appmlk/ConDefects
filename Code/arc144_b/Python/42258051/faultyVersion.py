N,a,b = map(int,input().split())
A = list(map(int,input().split()))

left = 0
right = 10**9

while right-left>1:
    mid = (left+right)//2
    plus_cnt = 0
    minus_cnt = 0
    for i in range(N):
        if A[i]<mid:
            plus_cnt += (mid-A[i]+a-1)//a
        else:
            minus_cnt += (A[i]-mid)//b

    if plus_cnt <= minus_cnt:
        left = mid
    else:
        right = mid

print(left)