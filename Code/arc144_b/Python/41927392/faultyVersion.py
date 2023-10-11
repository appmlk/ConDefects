def check(m, al, a, b):
    a_cnt = 0
    b_cnt = 0
    for num in al:
        diff = abs(num - m)
        if num < diff:
            cnt = diff // a
            if diff % a != 0:
                cnt += 1
            a_cnt += cnt
        else:
            cnt = diff // b
            b_cnt += cnt

    return a_cnt <= b_cnt


def job():
    n, a, b = map(int, input().split())
    al = list(map(int, input().split()))
    left = 0
    right = 10 ** 9 + 100
    while right - left > 1:
        mid = (right + left) // 2
        # もっと答えを大きくできるかも
        if check(mid, al, a, b):
            left = mid
        else:
            # もっとちいさく
            right = mid

    print(left)


job()
