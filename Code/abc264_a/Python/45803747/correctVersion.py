def func():
    # 入力を取得
    L, R = list(map(int, input().split()))
    
    s = "atcoder"
    print(s[L-1:R])

if __name__ == '__main__':
    func()