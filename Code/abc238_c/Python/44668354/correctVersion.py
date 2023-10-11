mod = 998244353

N = int(input())

def S(A,B):
    return (B-A+1)*(A+B)//2
    
ans = 0

for x in range(1,19):
    if 10**x <= N:
        ans += S(1,9*10**(x-1))
        ans %= mod
    else:
        ans += S(1,N-10**(x-1)+1)
        ans %= mod
        break
print(ans)