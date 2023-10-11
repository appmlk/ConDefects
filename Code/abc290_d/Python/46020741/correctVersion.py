#ABC290-D
import math
T = int(input())
for _ in range(T):
    N, D, K = map(int, input().split())
    K -= 1 #0-indexedに直す
    x = N//math.gcd(N, D)
    #何周期目か
    i = K//x
    #何番目か
    j = K%x
    
    print(((j*D + i)%N))