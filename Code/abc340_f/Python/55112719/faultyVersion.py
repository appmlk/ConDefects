def extended_gcd(a, b):
    """拡張ユークリッドの互除法を使用して ax + by = gcd(a, b) の解を求める"""
    if a == 0:
        return b, 0, 1
    gcd, x1, y1 = extended_gcd(b % a, a)
    x = y1 - (b // a) * x1
    y = x1
    return gcd, x, y

def find_integer_solution(X, Y):
    gcd, A, B = extended_gcd(X, Y)
    
    if 2 % gcd != 0:
        return None  # 解なし
    
    factor = 2 // gcd
    A *= factor
    B *= factor
    
    return A, -B  # Yの係数が -B になるように調整

# 例として X=1, Y=2 を与える
X,Y = list(map(int,input().split()))

solution = find_integer_solution(X, Y)
if solution:
    A, B = solution
    print(A,B)
else:
    print(-1)
