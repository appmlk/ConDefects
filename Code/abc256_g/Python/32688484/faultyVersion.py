MOD = 998244353

def mod_inv(x, m):
    a, b, c, d = x, 1, m-x, m-1

    while c != 1:
        if c == 0:
            print(x, m)
        e, f = divmod(a, c)
        a, b, c, d = c, d, f, (b-e*d)%m
    
    return d


F = [0]*(10**4+2)

def init_factors():
    F[0] = 1
    x = 1

    for i in range(1, 10**4+2):
        x = x * i % MOD
        F[i] = x



init_factors()

def get_comb(n, r):
    if r < 0:
        return 0

    return F[n]*mod_inv(F[r]*F[n-r]%MOD, MOD)%MOD


def mult_mat(M1, M2):
    return [[sum(M1[i][k]*M2[k][j]%MOD for k in range(2)) for j in range(2)] for i in range(2)]


def pow_mat(M, n):
    M2 = [[1,0],[0,1]]

    while n > 0:
        if n%2 == 1:
            M2 = mult_mat(M2, M)

        M = mult_mat(M, M)
        n //= 2

    return M2



def solve():
    N, D = map(int, input().split())
    ans = 0
    L = D+1

    for k in range(L//2 + 1):
        a = get_comb(L-2, k)
        b = get_comb(L-2, k-1)
        c = get_comb(L-2, k-2)

        M = [[a, b],[b, c]]
        M2 = pow_mat(M, N)
        
        ans += (M2[0][0] + M2[1][1])*(1 if L%2==0 and k == L//2 else 2)
        
    print(ans%MOD)

solve()








