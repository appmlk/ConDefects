def func():
    pattern1 = ["H","D","C","S"]
    pattern2 = ["A","2","3","4","5","6","7","8","9","T","J","Q","K"]
    pat_set = set()
    
    # 入力を取得
    N = int(input())
    for n in range(N):
      s = input()
      if (not(s[0] in pattern1)) or (not(s[1] in pattern2)) or (not(s in pat_set)):
        print("No")
        return
      
      pat_set.add(s)
      
    
    print("Yes")

if __name__ == '__main__':
    func()