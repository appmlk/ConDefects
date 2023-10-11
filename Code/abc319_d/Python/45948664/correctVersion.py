N,M = map(int,input().split())
L = list(map(int,input().split()))

def f(w) -> bool:
    # 0の場合を特別化する
    n_row = 0
    # 残り文字数を管理
    rem = 0
    for i in range(N):
        # もし残り文字数が十分であるならば、それをremから引く
        if rem >= L[i]+1:
            rem -= L[i]+1
        else:
            n_row += 1
            rem = w - L[i]
            if rem < 0 : return False
    if n_row <= M:
        return True
    else: return False

l = 0
r = 1000000000000000


while (r-l) > 1:
    m = (l+r)//2

    if f(m) == True:
        r = m
    else: #f(m) == False
        l = m

print(r)

