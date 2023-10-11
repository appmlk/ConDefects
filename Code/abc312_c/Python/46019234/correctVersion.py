N,M = map(int,input().split())
A = list(map(int,input().split()))
B = list(map(int,input().split()))

A.sort()
B.sort()

def count_seller(mid):
    cnt = 0
    for i in range(len(A)):
        if A[i] <= mid:
            cnt += 1
    return cnt

def count_buyer(mid):
    cnt = 0
    for i in range(len(B)):
        if B[i] >= mid:
            cnt += 1
    return cnt


# 二分探索する

left = -1
right = 10000000000

while right > (1 + left):
    mid = (left + right) // 2
    # mid円における売り手の人数
    n_of_seller = count_seller(mid)
    # mid円における買い手の人数
    n_of_buyer = count_buyer(mid)
    # 売り手＝買い手の場合、rightを出力するようにしたいので、等号はこっちに入れる。
    if n_of_seller >= n_of_buyer:
        right = mid
    else: #n_of_seller < n_of_buyer
        left = mid
print(right)


