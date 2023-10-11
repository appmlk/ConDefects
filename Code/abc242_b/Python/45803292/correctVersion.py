def func():
    # 入力を取得
    S = input()
    
    print(*sorted(S), sep='')

if __name__ == '__main__':
    func()