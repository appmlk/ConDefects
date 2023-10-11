def func():
    # 入力を取得
    N = int(input())
    A = list(map(int, input().split()))
    
    P = 0
    
    for n in range(N):
      if sum(A[n:]) > 4:
        P += 1
    
    print(P)
    
if __name__ == '__main__':
    func()