def solve(N, K):
    def rev(x):
        return int(''.join(reversed(list(str(x)))))

    def cnt(x):
        res = 0
        while x <= N:
            res += 1
            x *= 10
        
        return res
    
    if K % 10 == 0:
        return 0

    if K > rev(K):
        return 0

    return cnt(K) + (cnt(rev(K)) if K != rev(K) else 0)

N, K = map(int, input().split())
print(solve(N, K))