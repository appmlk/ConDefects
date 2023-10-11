def check_ans(W):
    '''
    ある値でOKか否かの判定関数。問題に合わせて変更する。
    '''
    line_cnt = 1
    line_char_cnt = 0

    if L[0] > W:
        return False
    else:
        line_char_cnt = L[0]

    for i in range(1,N):
        if L[i] > W:
            return False
        else:
            if line_char_cnt + 1 + L[i] <= W:
                line_char_cnt += 1 + L[i]
            else:
                line_char_cnt = L[i]
                line_cnt += 1

                if line_cnt > M:
                    return False
    
    return True

def binary_search(maxi,mini):
    '''
    mini～maxi間で判定用関数の結果がOKとなる最小値を求める
    '''
    
    if maxi - mini <= 4:
        for ans in range(maxi,mini,-1):
            if check_ans(ans) == False:
                return ans + 1
    else:
        mid = (maxi+mini)//2        #int((max+min)//2)だと浮動小数点を介して、誤差になるので注意
        
        #判定用関数に投げて、条件により最大・最少を狭める
        if check_ans(mid) == True:
            # midでOKだったので、OKとなる最小値はmidより小さい値である
            maxi = mid
        else:
            # midでNGだったので、OKとなる最小値はmidより大きい値である
            mini = mid
        
        return binary_search(maxi,mini)

N,M = map(int, input().split())
L = [int(e) for e in input().split()]

print(binary_search(10**16,0))