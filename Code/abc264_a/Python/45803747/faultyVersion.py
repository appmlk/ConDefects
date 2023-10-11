def func():
    # 入力を取得
    L, R = list(map(int, input().split()))
    
    s = "atcoder"
    print(s[L+1:R+1])

if __name__ == '__main__':
    func()