import math
T = int(input())
for _ in range(T):
    N = int(input())
    num = int(math.sqrt(N)+1e-9)
    ans = num ** 3
    for y in range(1, num+1):
        num_x = y
        num_z = N // y - num
        ans += (num_x-1) * num_z * 6 + num_z*3
        ans %= 998244353        
    print(ans)