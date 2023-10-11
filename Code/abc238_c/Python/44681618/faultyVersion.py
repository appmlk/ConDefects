def sum_of_f(n):
    ans = 0
    n1 = 1
    while 10*n1 < n:
        ans += (10*n1-1-n1+1)*(10*n1-1-n1+1+1)//2
        ans = ans % 998244353
        n1 *= 10
    ans += (n-n1+1)*(n-n1+1+1)//2
    ans = ans % 998244353
    return ans

n = int(input())
print(sum_of_f(n))