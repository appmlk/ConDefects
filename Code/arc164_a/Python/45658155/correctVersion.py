t = int(input())

for i in range(t):
    n, k = map(int, input().split())

    if n % 2 != k % 2 or n < k:
        print("No")
    
    else:
        pre = n
        count = 0
        while True:
            count += pre % 3
            pre = pre // 3
            
            if pre < 3:
                count += pre
                break
        
        if count <= k:
            print("Yes")
        else:
            print("No")
