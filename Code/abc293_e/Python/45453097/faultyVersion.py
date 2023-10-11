A, X, MOD = map(int, input().split())

def f(x):
    if x == 0:
        return 0
    
    if x == 1:
        return 1
    
    res = f(x//2)
    res += res * pow(A, x//2, MOD)
    if x % 2 == 1:
        res += pow(A, x-1, MOD)
    res %= MOD
    return res

print(f(X))